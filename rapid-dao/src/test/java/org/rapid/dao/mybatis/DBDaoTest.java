package org.rapid.dao.mybatis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.rapid.dao.BaseTest;
import org.rapid.dao.Condition;
import org.rapid.dao.Query;

public class DBDaoTest extends BaseTest {

	@Resource
	private BasUserDao basUserDao;
	@Resource
	private MemTriggerDao memTriggerDao;
	
	@Test
	public void test() { 
		BasUser basUser = basUserDao.getByIdsss(10099937);
		System.out.println(basUser.getId() + " " + basUser.getMobile() + " " + basUser.getLastLoginIp());
		basUser = basUserDao.getByKey(10099937);
		System.out.println(basUser.getId() + " " + basUser.getMobile() + " " + basUser.getLastLoginIp());
		
		Set<Integer> set = new HashSet<>();
		set.add(10099937);
		set.add(10099953);
		Map<Integer, BasUser> users = basUserDao.getByKeys(set);
		for (BasUser user : users.values()) 
			System.out.println(user.getLastLoginIp() + " " + user.getMobile());
	}
	
	@Test
	public void testInsert() { 
		List<MemTrigger> list = new ArrayList<MemTrigger>();
		MemTrigger trigger = new MemTrigger();
		trigger.setKey("ss");
		list.add(trigger);
		
		trigger = new MemTrigger();
		trigger.setKey("ssddd");
		trigger.setUid(20);
		trigger.setCount(20);
		trigger.setTriggerId(1);
		list.add(trigger);
		memTriggerDao.batchInsert(list);
		for (MemTrigger memTrigger : list)
			System.out.println(memTrigger.getId());
	}
	
	@Test
	public void testUpdate() {
		Set<Integer> set = new HashSet<>();
		set.add(119);
		set.add(120);
		Map<Integer, MemTrigger> triggers = memTriggerDao.getByKeys(set);
//		for (MemTrigger trigger : triggers.values())
//			trigger.setCreated(10);
//		System.out.println(memTriggerDao.batchUpdate(triggers));
	}
	
	@Test
	public void testDelete() {
//		System.out.println(memTriggerDao.deleteByKey(114));
		Set<Integer> set = new HashSet<>();
		set.add(118);
		set.add(117);
		System.out.println(memTriggerDao.deleteByKeys(set));
	}
	
	@Test
	public void testQuery() {
		Condition condition = new Condition();
		condition.equal("mobile", 1);
		Query query = new Query();
		query.condition(condition).all().forUpdate();
		BasUser user = basUserDao.queryUnique(query);
		System.out.println(user);
		System.out.println(user.getUsername());
		List<BasUser> list = basUserDao.queryPage(query);
		System.out.println(list.size());
		Map<Integer, BasUser> users = basUserDao.query(query);
		System.out.println(users.size());
	}
}
