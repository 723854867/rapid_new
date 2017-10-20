package org.rapid.dao.redis;

import java.io.IOException;

import org.rapid.util.common.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * redis 操作客户端
 * 
 * @author lynn
 */
public class Redis {
	
	private static final Logger logger = LoggerFactory.getLogger(Redis.class);

	private Pool<Jedis> pool;
	private LuaFiber luaFiber;
	
	public Redis() {
		try {
			this.luaFiber = new LuaFiber(this);
		} catch (IOException e) {
			logger.error("Lua scrip load failure, system will closed...", e);
		}
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param captchaKey 存放验证码的 key
	 * @param countKey 存放验证码获取次数的 key
	 * @param captcha 验证码
	 * @param lifeTime 验证码有效时长，单位毫秒
	 * @param countMaxinum 验证码最大获取次数
	 * @param countLifetTime 验证码次数生命周期(超过该时间没有获取验证码，则验证码次数 key 会被删除，也就是说验证码次数会被清零)，单位毫秒
	 * @return 0 - 表示成功；-1 - 表示获取验证码获取太频繁，-2 - 表示验证码获取次数上限
	 */
	public long captchaObtain(String captchaKey, String countKey, String captcha, long lifeTime, long countMaxinum, long countLifetTime) {
		return invoke(DefaultLuaCmd.CAPTCHA_OBTAIN, captchaKey, countKey, captcha, lifeTime, countMaxinum, countLifetTime);
	}
	
	/**
	 * 如果 key 值存在并且值等于 value 则删除 value 然后返回 true，否则什么也不做返回 false
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean delIfEquals(String key, String value) {
		long flag = invoke(DefaultLuaCmd.DEL_IF_EQUALS, key, value);
		return flag == 1;
	}
	
	public <T> T invoke(LuaCmd cmd, Object... params) {
		return luaFiber.invoke(cmd, cmd.keyNum(), params);
	}
	
	/**
	 * 执行普通的 redis 命令
	 * 
	 * @param invoke
	 * @return
	 */
	<T> T invoke(Callback<Jedis, T> invoke) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return invoke.invoke(jedis);
		} finally {
			jedis.close();
		}
	}
	
	public void setPool(Pool<Jedis> pool) {
		this.pool = pool;
	}
}
