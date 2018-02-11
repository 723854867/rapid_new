package org.rapid.dao.mybatis;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import org.rapid.util.common.Assert;
import org.rapid.util.lang.StringUtil;

public class EntityClass {
	
	private Class<?> clazz;
	private String tableName;														// 表名：默认驼峰转下划线
	private EntityField primary;
	private Map<String, EntityField> fields = new HashMap<String, EntityField>();	// 字段映射

	public EntityClass(Class<?> clazz) {
		this.clazz = clazz;
		_init();
		_initField();
	}
	
	private void _init() { 
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			this.tableName = table.name();
		}
		if (!StringUtil.hasText(tableName)) 
			this.tableName = StringUtil.camel2Underline(clazz.getSimpleName());
	}
	
	private void _initField() { 
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()))
				continue;
			EntityField ef = new EntityField(field);
			this.fields.put(field.getName(), ef);
			if (ef.isPrimary()) {
				Assert.isNull(this.primary, "primary");
				this.primary = ef;
			}
		}
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public String getKeyCol() {
		return this.primary.getColName();
	}
	
	public String getKeyField() {
		return this.primary.getFieldName();
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public EntityField getPrimary() {
		return primary;
	}
	
	public Map<String, EntityField> getFields() {
		return fields;
	}
}
