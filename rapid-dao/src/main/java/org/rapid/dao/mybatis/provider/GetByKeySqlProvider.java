package org.rapid.dao.mybatis.provider;

import org.rapid.dao.mybatis.EntityClass;
import org.rapid.util.common.Assert;
import org.rapid.util.common.model.enums.OperType;

public class GetByKeySqlProvider extends SqlProvider {
	
	@Override
	public OperType operType() {
		return OperType.SELECT;
	}

	@Override
	public String sql(EntityClass entityClass) {
		Assert.notNull("Entity class must marked with id annotation!", entityClass.getPrimary());
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ").append(entityClass.getTableName())
				.append(" WHERE ").append(entityClass.getKeyCol()).append("=#{")
				.append(entityClass.getKeyField()).append("}");
		return builder.toString();
	}
}
