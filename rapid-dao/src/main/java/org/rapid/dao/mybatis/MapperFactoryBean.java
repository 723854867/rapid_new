package org.rapid.dao.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapperFactoryBean<T> extends org.mybatis.spring.mapper.MapperFactoryBean<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(MapperFactoryBean.class);
	
	private MapperHelper mapperHelper;
	
	public MapperFactoryBean() {}

    public MapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }
    
    public void setMapperHelper(MapperHelper mapperHelper) {
		this.mapperHelper = mapperHelper;
	}
    
    @Override
    protected void checkDaoConfig() {
        super.checkDaoConfig();
        Class<T> mapperInterface = getObjectType();
        if (DBDao.class.isAssignableFrom(mapperInterface)) {					// 继承自通用接口接口
        	String prefix = mapperInterface.getCanonicalName();
        	Configuration configuration = getSqlSession().getConfiguration();
        	for (Object object : new ArrayList<Object>(configuration.getMappedStatements())) {
                if (object instanceof MappedStatement) {
                    MappedStatement ms = (MappedStatement) object;
                    if (ms.getId().startsWith(prefix) && ms.getSqlSource() instanceof ProviderSqlSource) {
						try {
							this.mapperHelper.reset(ms, getObjectType());
						} catch (Exception e) {
							logger.error("Mapper scanner failure!", e);
						}
                    }
                }
            }
        }
    }
}
