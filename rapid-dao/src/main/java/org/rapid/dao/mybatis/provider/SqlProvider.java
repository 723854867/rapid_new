package org.rapid.dao.mybatis.provider;

import org.rapid.dao.mybatis.EntityClass;
import org.rapid.util.common.model.enums.OperType;

public abstract class SqlProvider {
	
	public String dynamicSQL() {
		return "dynamicSQL";
	}
	
	public abstract OperType operType();
	
	public abstract String sql(EntityClass entityClass);
}
