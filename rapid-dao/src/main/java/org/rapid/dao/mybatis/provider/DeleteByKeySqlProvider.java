package org.rapid.dao.mybatis.provider;

import org.rapid.dao.mybatis.EntityClass;
import org.rapid.util.common.model.enums.OperType;

public class DeleteByKeySqlProvider extends SqlProvider {

	@Override
	public OperType operType() {
		return OperType.DELETE;
	}

	@Override
	public String sql(EntityClass entityClass) {
		return new StringBuilder("DELETE FROM ").append(entityClass.getTableName())
					.append(" WHERE `").append(entityClass.getKeyCol()).append("`=#{")
					.append(entityClass.getKeyField()).append("}").toString();
	}
}
