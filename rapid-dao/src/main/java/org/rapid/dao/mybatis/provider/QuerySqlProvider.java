package org.rapid.dao.mybatis.provider;

import org.rapid.dao.mybatis.EntityClass;
import org.rapid.util.common.model.enums.OperType;

public class QuerySqlProvider extends SqlProvider {
	
	private static final String foreachSuffix = "</foreach>";
	
	@Override
	public OperType operType() {
		return OperType.SELECT;
	}

	@Override
	public String sql(EntityClass entityClass) {
		StringBuilder builder = new StringBuilder("SELECT ")
				.append("<foreach item=\"item\" collection=\"query.selects\" open=\"\" close=\"\" separator=\",\">")
					.append("${item}")
				.append(foreachSuffix)
				.append(" FROM ").append(entityClass.getTableName());
		builder.append("<if test=\"query.condition!=null\">").append("<where>")
					.append("<foreach item=\"item\" collection=\"query.condition.equals\" open=\"\" close=\"\" separator=\"\">")
						.append("AND `${item.col}`=#{item.value} ")
					.append(foreachSuffix)
					.append("<foreach item=\"item\" collection=\"query.condition.notEquals\" open=\"\" close=\"\" separator=\"\">")
						.append("AND ${item.col}!=#{item.value} ")
					.append(foreachSuffix)
					.append("<foreach item=\"item\" index=\"index\" collection=\"query.condition.ins\"  separator=\" AND \">")
						.append(" AND ${index} IN")
							.append("<foreach item=\"item1\" collection=\"item\" open=\"(\" close=\")\" separator=\",\">")
								.append("#{item1}")
							.append(foreachSuffix)
					.append(foreachSuffix)
					.append("<foreach item=\"item\" index=\"index\" collection=\"query.condition.notIns\"  separator=\" AND \">")
							.append(" AND ${index} NOT IN")
								.append("<foreach item=\"item1\" collection=\"item\" open=\"(\" close=\")\" separator=\",\">")
									.append("#{item1}")
								.append(foreachSuffix)
					.append(foreachSuffix)
				.append("</where></if>");
		builder.append("<if test=\"null != query.groupBies\">")
					.append("GROUP BY ")
					.append("<foreach item=\"item\" collection=\"query.groupBies\" separator=\",\">")
						.append("`${item}`")
					.append(foreachSuffix)
				.append("</if>");
		builder.append("<if test=\"null != query.orderBies\">")
				.append("ORDER BY ")
				.append("<foreach item=\"item\" collection=\"query.orderBies\" separator=\",\">")
					.append("`${item.col}` ${item.order}")
				.append(foreachSuffix)
				.append("</if>");
		builder.append("<if test=\"query.forUpdate\">")
					.append(" FOR UPDATE")
				.append("</if>");
		return builder.toString();
	}
}
