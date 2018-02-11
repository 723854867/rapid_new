package org.rapid.dao.redis;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.rapid.dao.Dao;
import org.rapid.dao.Query;
import org.rapid.util.common.model.Unique;
import org.rapid.util.common.serializer.Serializer;

public class RedisDao<KEY, ENTITY extends Unique<KEY>> implements Dao<KEY, ENTITY> {

	@Resource
	private Redis redis;
	private Serializer serializer;
	
	public RedisDao() {
	}

	@Override
	public void insert(ENTITY entity) {
	}

	@Override
	public void batchInsert(Collection<ENTITY> entities) {
		
	}

	@Override
	public ENTITY getByKey(KEY key) {
		return null;
	}

	@Override
	public Map<KEY, ENTITY> getByKeys(Collection<KEY> keys) {
		return null;
	}

	@Override
	public Map<KEY, ENTITY> query(Query query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ENTITY entity) {
		return 0;
	}

	@Override
	public int batchUpdate(Map<KEY, ENTITY> entities) {
		return 0;
	}

	@Override
	public int deleteByKey(KEY key) {
		return 0;
	}

	@Override
	public int deleteByKeys(Collection<KEY> keys) {
		return 0;
	}
}
