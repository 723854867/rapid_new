package org.rapid.dao.mybatis;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.util.lang.StringUtil;

public class EntityField {
	
	private Class<?> clazz;
	private String colName;					// 数据库对应列名
	private String fieldName;				// 属性名
	private boolean primary;				// 是否是主键
	private boolean useGeneratedKeys;		// 是否使用主键自动生成策略
	private boolean updatable = true;		// 是否允许更新，默认为true
	
	public EntityField(Field field) {
		clazz = field.getClass();
		this.fieldName = field.getName();
		if (field.isAnnotationPresent(Id.class)) 
			this.primary = true;
		if (field.isAnnotationPresent(Column.class)) {
			Column column = field.getAnnotation(Column.class);
			this.colName = column.name();
			this.updatable = column.updatable();
		}
		if (field.isAnnotationPresent(GeneratedValue.class))
			this.useGeneratedKeys = true;
		if (!StringUtil.hasText(colName))
			this.colName = StringUtil.camel2Underline(field.getName());
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public String getColName() {
		return colName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
	public boolean isUpdatable() {
		return updatable;
	}
	
	public boolean isUseGeneratedKeys() {
		return useGeneratedKeys;
	}
}
