package org.rapid.dao.redis;

import javax.annotation.Resource;

import org.rapid.util.common.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * redis 操作客户端
 * 
 * @author lynn
 */
@Component
public class Redis {
	
	@Resource
	private LuaFiber luaFiber;
	@Autowired(required = false)
	private Pool<Jedis> jedisPool;
	
	/**
	 * 获取验证码
	 * 
	 * @param captchaKey 存放验证码的 key
	 * @param countKey 存放验证码获取次数的 key
	 * @param captcha 验证码
	 * @param lifeTime 验证码有效时长，单位毫秒
	 * @param countMaxinum 验证码最大获取次数
	 * @param countLifetTime 验证码次数生命周期(超过该时间没有获取验证码，则验证码次数 key 会被删除，也就是说验证码次数会被清零)，单位毫秒
	 * @param interval 两次获取验证码之间的时间间隔：在该时间之内再次获取会提示验证码获取太频繁
	 * @return 0 - 表示成功；-1 - 表示获取验证码获取太频繁，-2 - 表示验证码获取次数上限
	 */
	public long captchaObtain(String captchaKey, String countKey, String captcha, long lifeTime, long countMaxinum, long countLifetTime, int interval) {
		return this.luaFiber.invoke(LuaCmd.CAPTCHA_OBTAIN, captchaKey, countKey, captcha, lifeTime, countMaxinum, countLifetTime, interval);
	}
	
	/**
	 * 如果 key 值存在并且值等于 value 则删除 value 然后返回 true，否则什么也不做返回 false
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean delIfEquals(String key, String value) {
		long flag = this.luaFiber.invoke(LuaCmd.DEL_IF_EQUALS, key, value);
		return flag == 1;
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
			jedis = jedisPool.getResource();
			return invoke.invoke(jedis);
		} finally {
			jedis.close();
		}
	}
}
