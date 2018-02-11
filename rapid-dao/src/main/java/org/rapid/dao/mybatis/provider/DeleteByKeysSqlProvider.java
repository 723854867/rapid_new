package org.rapid.dao.mybatis.provider;

import org.rapid.dao.mybatis.EntityClass;
import org.rapid.util.common.model.enums.OperType;

public class DeleteByKeysSqlProvider extends SqlProvider {
	
	private static final String foreachSuffix = "</foreach>";
	private static final String foreachPrefix = "<foreach item=\"item\" collection=\"collection\" separator=\",\" open=\"(\" close=\")\">";

	@Override
	public OperType operType() {
		return OperType.DELETE;
	}

	@Override
	public String sql(EntityClass entityClass) {
		return new StringBuilder("DELETE FROM ").append(entityClass.getTableName())
				.append(" WHERE `").append(entityClass.getKeyCol()).append("` IN").append(foreachPrefix)
				.append("#{item}").append(foreachSuffix).toString();
	}
}
