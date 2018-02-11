package org.rapid.dao;

import java.util.Collection;
import java.util.Map;

import org.rapid.util.common.model.Unique;

/**
 * Data Access Object
 * 
 * @author lynn
 */
public interface Dao<KEY, ENTITY extends Unique<KEY>> {

	/**
	 * 插入一个对象
	 * 
	 * @param entity
	 */
	void insert(ENTITY entity);
	
	/**
	 * 插入多个对象
	 * 
	 * @param entities
	 */
	void batchInsert(Collection<ENTITY> entities);
	
	/**
	 * 根据主键获取对象
	 * 
	 * @param key
	 */
	ENTITY getByKey(KEY key);
	
	/**
	 * 一次获取多条数据
	 * 
	 * @return
	 */
	Map<KEY, ENTITY> getByKeys(Collection<KEY> keys);
	
	/**
	 * 根据条件查询
	 * 
	 * @return
	 */
	Map<KEY, ENTITY> query(Query query);
	
	/**
	 * 更新对象：是更新对象的所有属性，因此要注意每个属性是否都是最新的
	 * 
	 * @param entity
	 */
	int update(ENTITY entity);
	
	/**
	 * 批量更新
	 * 
	 * @param entities
	 */
	int batchUpdate(Map<KEY, ENTITY> entities);
	
	/**
	 * 根据主键删除
	 * 
	 * @param key
	 */
	int deleteByKey(KEY key);
	
	/**
	 * 批量删除
	 * 
	 * @param keys
	 */
	int deleteByKeys(Collection<KEY> keys);
}
