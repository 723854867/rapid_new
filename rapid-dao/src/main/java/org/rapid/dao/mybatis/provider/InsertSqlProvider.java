package org.rapid.dao.mybatis.provider;

import org.rapid.dao.mybatis.EntityClass;
import org.rapid.dao.mybatis.EntityField;
import org.rapid.util.common.model.enums.OperType;

public class InsertSqlProvider extends SqlProvider {
	
	@Override
	public OperType operType() {
		return OperType.INSERT;
	}
	
	@Override
	public String sql(EntityClass entityClass) {
		StringBuilder builder = new StringBuilder("INSERT INTO ")
				.append(entityClass.getTableName()).append("(");
		EntityField[] fields = entityClass.getFields().values().toArray(new EntityField[] {});
		for (EntityField field : fields)
			builder.append("`").append(field.getColName()).append("`,");
		builder.deleteCharAt(builder.length() - 1).append(") VALUES(");
		for (EntityField field : fields)
			builder.append("#{").append(field.getFieldName()).append("},");
		builder.deleteCharAt(builder.length() - 1).append(")");
		return builder.toString();
	}
}
