package org.rapid.dao;

import java.util.Map;

/**
 * Data Access Object
 * 
 * @author lynn
 */
public interface Dao<KEY, ENTITY extends Entity<KEY, ?>, BUILDER extends EntityBuilder<KEY, ?>> {

	/**
	 * 插入一个对象
	 * 
	 * @param entity
	 */
	void insert(ENTITY entity);
	
	/**
	 * 根据主键获取对象
	 * 
	 * @param key
	 */
	ENTITY getByKey(KEY key);
	
	/**
	 * 通过属性获取多个对象
	 * 
	 * @return
	 */
	Map<KEY, ENTITY> getByProperties(BUILDER builder);
	
	/**
	 * 更新对象：是更新对象的所有属性，因此要注意每个属性是否都是最新的
	 * 
	 * @param entity
	 */
	void update(ENTITY entity);
	
	/**
	 * 根据主键删除
	 * 
	 * @param key
	 */
	void deleteByKey(KEY key);
	
	/**
	 * 根据属性删除：有可能删除多条数据
	 * 
	 * @param builder
	 */
	void deleteByProperties(BUILDER builder);
}
