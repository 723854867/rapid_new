package org.rapid.dubbo.demo.service;

import org.rapid.dubbo.demo.api.DemoService;
import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Override
	public String helloWorld(String content) {
		System.out.println("accept : " + content);
		return "hello word";
	}
}
