package org.rapid.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapid.util.common.model.code.Code;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/spring/*.xml")
public class BaseTest {

	@Test
	public void test() {
		for (Code code : Code.values()) {
			System.out.println(code.desc());
		}
	}
}
