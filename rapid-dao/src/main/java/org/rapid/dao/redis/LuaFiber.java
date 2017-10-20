package org.rapid.dao.redis;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.rapid.util.collection.CollectionUtil;
import org.rapid.util.common.Callback;
import org.rapid.util.common.serializer.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisNoScriptException;

/**
 * Lua 命令执行器
 * 
 * @author lynn
 */
class LuaFiber {
	
	private static final Logger logger = LoggerFactory.getLogger(LuaFiber.class);

	private Redis redis;
	private Map<LuaCmd, Script> scripts = new ConcurrentHashMap<LuaCmd, Script>();			
	
	LuaFiber(Redis redis) throws IOException {
		this.redis = redis;
		ServiceLoader<LuaScriptLoader> services = ServiceLoader.load(LuaScriptLoader.class);
		for (LuaScriptLoader loader : services) {
			Map<LuaCmd, byte[]> buffers = loader.load();
			if (CollectionUtil.isEmpty(buffers))
				continue;
			for (Entry<LuaCmd, byte[]> entry : buffers.entrySet()) {
				Script script = new Script(entry.getValue());
				if (null != scripts.putIfAbsent(entry.getKey(), script))
					logger.warn("存在多个同名的 lua 脚本 - {}！", entry.getKey());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	<T> T invoke(LuaCmd cmd, int keyNum, Object... params) {
		Script script = scripts.get(cmd);
		if (null == script)
			throw new JedisNoScriptException("Script " + cmd + " not exist!");
		byte[][] arr = SerializeUtil.RedisUtil.encode(params);
		return redis.invoke(new Callback<Jedis, T>() {
			@Override
			public T invoke(Jedis jedis) {
				if (script.stored) {
					try {
						return (T) jedis.evalsha(SerializeUtil.RedisUtil.encode(script.sha1Key), keyNum, arr);
					} catch (JedisNoScriptException e) {
						logger.warn("redis lua 脚本 - {} 缓存未命中,直接执行脚本！", cmd);
					}
				}
				T object = (T) jedis.eval(SerializeUtil.RedisUtil.encode(script.content), keyNum, arr);
				script.stored = true;
				return object;
			}
		});
	}
	
	private class Script {
		private String sha1Key;
		private String content;
		private boolean stored;
		private Script(byte[] content) {
			this.sha1Key = DigestUtils.sha1Hex(content);
			this.content = new String(content);
		}
	}
}
