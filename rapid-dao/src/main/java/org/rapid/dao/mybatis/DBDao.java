package org.rapid.dao.mybatis;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.rapid.dao.Dao;
import org.rapid.dao.Query;
import org.rapid.dao.mybatis.provider.BatchInsertSqlProvider;
import org.rapid.dao.mybatis.provider.BatchUpdateSqlProvider;
import org.rapid.dao.mybatis.provider.DeleteByKeySqlProvider;
import org.rapid.dao.mybatis.provider.DeleteByKeysSqlProvider;
import org.rapid.dao.mybatis.provider.GetByKeySqlProvider;
import org.rapid.dao.mybatis.provider.GetByKeysSqlPrivoder;
import org.rapid.dao.mybatis.provider.QuerySqlProvider;
import org.rapid.dao.mybatis.provider.UpdateSqlProvider;
import org.rapid.util.common.model.Unique;

public interface DBDao<KEY, ENTITY extends Unique<KEY>> extends Dao<KEY, ENTITY> {

	@Override
	@InsertProvider(type = GetByKeySqlProvider.class, method = "dynamicSQL")
	void insert(ENTITY entity);
	
	@Override
	@InsertProvider(type = BatchInsertSqlProvider.class, method = "dynamicSQL")
	void batchInsert(Collection<ENTITY> entities);
	
	@Override
	@SelectProvider(type = GetByKeySqlProvider.class, method = "dynamicSQL")
	ENTITY getByKey(KEY key);
	
	@Override
	@SelectProvider(type = GetByKeysSqlPrivoder.class, method = "dynamicSQL")
	Map<KEY, ENTITY> getByKeys(Collection<KEY> keys);
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @return
	 */
	@SelectProvider(type = QuerySqlProvider.class, method = "dynamicSQL")
	List<ENTITY> queryPage(@Param("query") Query query);
	
	@Override
	@SelectProvider(type = QuerySqlProvider.class, method = "dynamicSQL")
	Map<KEY, ENTITY> query(@Param("query") Query query);
	
	@SelectProvider(type = QuerySqlProvider.class, method = "dynamicSQL")
	ENTITY queryUnique(@Param("query") Query query);
	
	@Override
	@UpdateProvider(type = UpdateSqlProvider.class, method = "dynamicSQL")
	int update(ENTITY entity);
	
	@Override
	@UpdateProvider(type = BatchUpdateSqlProvider.class, method = "dynamicSQL")
	int batchUpdate(@Param("map") Map<KEY, ENTITY> entities);
	
	@Override
	@DeleteProvider(type = DeleteByKeySqlProvider.class, method = "dynamicSQL")
	int deleteByKey(KEY key);
	
	@Override
	@DeleteProvider(type = DeleteByKeysSqlProvider.class, method = "dynamicSQL")
	int deleteByKeys(Collection<KEY> keys);
}
