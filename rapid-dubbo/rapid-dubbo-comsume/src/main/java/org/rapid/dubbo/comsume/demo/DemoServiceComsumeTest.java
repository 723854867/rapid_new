package org.rapid.dubbo.comsume.demo;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.rapid.dubbo.comsume.BaseTest;
import org.rapid.dubbo.demo.api.DemoService;

public class DemoServiceComsumeTest extends BaseTest {

	@Resource
	private DemoService demoService;
	
	@Test
	public void testHelloWorld() {
		String result = demoService.helloWorld("hello world server!");
		Assert.assertEquals(result, "hello word");
	}
}
