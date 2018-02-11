package org.rapid.dao.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.dao.BaseTest;

public class RedisTest extends BaseTest {
	
	@Resource
	private Redis redis;

	@Test
	public void testDelIfEquals() {
		System.out.println(redis.delIfEquals("sd", "10"));
	}
	
	@Test
	public void testCaptchaObtain() {
		System.out.println(redis.captchaObtain("sdsd", "sss", "5624", 60000, 6, 600000, 20000));
	}
}
