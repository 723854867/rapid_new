package org.rapid.dao.redis;

import java.util.Map;

import javax.annotation.Resource;

import org.rapid.dao.Dao;
import org.rapid.dao.Entity;
import org.rapid.dao.EntityBuilder;

public class RedisDao<KEY, ENTITY extends Entity<KEY, ?>, BUILDER extends EntityBuilder<KEY, ?>> implements Dao<KEY, ENTITY, BUILDER> {

	@Resource
	private Redis redis;
	
	public RedisDao() {
	}
	
	@Override
	public void insert(ENTITY entity) {
		
	}

	@Override
	public ENTITY getByKey(KEY key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<KEY, ENTITY> getByProperties(BUILDER builder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ENTITY entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByKey(KEY key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByProperties(BUILDER builder) {
		
	}
	
	public void setRedis(Redis redis) {
		this.redis = redis;
	}
}
