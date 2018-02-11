package org.rapid.dao.mybatis.provider;

import org.rapid.dao.mybatis.EntityClass;
import org.rapid.dao.mybatis.EntityField;
import org.rapid.util.common.Assert;
import org.rapid.util.common.model.enums.OperType;

public class UpdateSqlProvider extends SqlProvider {

	@Override
	public OperType operType() {
		return OperType.UPDATE;
	}

	@Override
	public String sql(EntityClass entityClass) {
		Assert.notNull("Entity class must marked with id annotation!", entityClass.getPrimary());
		StringBuilder builder = new StringBuilder("UPDATE ").append(entityClass.getTableName()).append(" SET ");
		EntityField[] fields = entityClass.getFields().values().toArray(new EntityField[] {});
		for (EntityField field : fields) {
			if (field.isPrimary())
				continue;
			builder.append("`").append(field.getColName()).append("`=#{").append(field.getFieldName()).append("},");
		}
		builder.deleteCharAt(builder.length() - 1).append(" WHERE ").append(entityClass.getKeyCol()).append("=#{")
			.append(entityClass.getKeyField()).append("}");
		return builder.toString();
	}
}
