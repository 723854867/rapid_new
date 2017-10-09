package org.rapid.dubbo.comsume;

import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/spring/*.xml");
		TimeUnit.HOURS.sleep(5);
	}
}
