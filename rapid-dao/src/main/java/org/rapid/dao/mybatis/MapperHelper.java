package org.rapid.dao.mybatis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.binding.MapperMethod.MethodSignature;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.rapid.dao.Query;
import org.rapid.dao.mybatis.provider.BatchInsertSqlProvider;
import org.rapid.dao.mybatis.provider.BatchUpdateSqlProvider;
import org.rapid.dao.mybatis.provider.DeleteByKeySqlProvider;
import org.rapid.dao.mybatis.provider.DeleteByKeysSqlProvider;
import org.rapid.dao.mybatis.provider.GetByKeySqlProvider;
import org.rapid.dao.mybatis.provider.GetByKeysSqlPrivoder;
import org.rapid.dao.mybatis.provider.InsertSqlProvider;
import org.rapid.dao.mybatis.provider.QuerySqlProvider;
import org.rapid.dao.mybatis.provider.SqlProvider;
import org.rapid.dao.mybatis.provider.UpdateSqlProvider;
import org.rapid.util.common.model.enums.OperType;
import org.rapid.util.lang.StringUtil;

public class MapperHelper {
	
    private final XMLLanguageDriver languageDriver = new XMLLanguageDriver();

	private Map<String, EntityClass> entities = new HashMap<String, EntityClass>();
	private Map<String, SqlProvider> sqlTemplates = new HashMap<String, SqlProvider>();
	
	private Field mapKey = null;
	private Field returnMap = null;
	private Field mapperMethod = null;
	private Field resultMapType = null;
	private Field modifierField = null;
	private Field konwnMappersField = null;
	
	public MapperHelper() {
		_init();
	}
	
	private void _init() { 
		Method[] methods = DBDao.class.getMethods();
		for (Method method : methods) {
			String name = method.getName();
			switch (name) {
			case "getByKey":
				sqlTemplates.put(name, new GetByKeySqlProvider());
				break;
			case "getByKeys":
				sqlTemplates.put(name, new GetByKeysSqlPrivoder());
				break;
			case "insert":
				sqlTemplates.put(name, new InsertSqlProvider());
				break;
			case "batchInsert":
				sqlTemplates.put(name, new BatchInsertSqlProvider());
				break;
			case "update":
				sqlTemplates.put(name, new UpdateSqlProvider());
				break;
			case "batchUpdate":
				sqlTemplates.put(name, new BatchUpdateSqlProvider());
				break;
			case "deleteByKey":
				sqlTemplates.put(name, new DeleteByKeySqlProvider());
				break;
			case "deleteByKeys":
				sqlTemplates.put(name, new DeleteByKeysSqlProvider());
				break;
			case "query":
			case "queryPage":
			case "queryUnique":
				sqlTemplates.put(name, new QuerySqlProvider());
				break;
			default:
				break;
			}
		}
		
		try {
			this.modifierField = Field.class.getDeclaredField("modifiers");
			this.modifierField.setAccessible(true);
			this.konwnMappersField = MapperRegistry.class.getDeclaredField("knownMappers");
			this.konwnMappersField.setAccessible(true);
			this.mapperMethod = MapperMethod.class.getDeclaredField("method");
			this.mapperMethod.setAccessible(true);
			this.mapKey = MethodSignature.class.getDeclaredField("mapKey");
			this.mapKey.setAccessible(true);
			this.returnMap = MethodSignature.class.getDeclaredField("returnsMap");
			this.returnMap.setAccessible(true);
			this.resultMapType = ResultMap.class.getDeclaredField("type");
			this.resultMapType.setAccessible(true);
			
			modifierField.setInt(this.mapKey, this.mapKey.getModifiers() & ~Modifier.FINAL);
			modifierField.setInt(this.returnMap, this.returnMap.getModifiers() & ~Modifier.FINAL);
		} catch (Exception e) {} 
	}
	
	public void reset(MappedStatement statement, Class<?> mapperInterface) throws Exception { 
		String msId = statement.getId();
		EntityClass ec = entities.get(msId);
        String methodName = msId.substring(msId.lastIndexOf(".") + 1);
		if (null == ec) {
			Type[] types = mapperInterface.getGenericInterfaces();
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType t = (ParameterizedType) type;
                    Class<?> returnType = (Class<?>) t.getActualTypeArguments()[1];
                    ec = new EntityClass(returnType);
                    entities.put(msId, ec);
                }
            }
            
            switch (methodName) {
			case "query":
				_registerMethodMapper(statement, mapperInterface, mapperInterface.getMethod("query", new Class[] {Query.class}), ec);
				break;
			case "getByKeys":
	            _registerMethodMapper(statement, mapperInterface, mapperInterface.getMethod("getByKeys", new Class[] {Collection.class}), ec);
				break;
			default:
				break;
			}
		}
        SqlProvider template = sqlTemplates.get(methodName);
        String sql = template.sql(ec);
		SqlSource sqlSource = languageDriver.createSqlSource(statement.getConfiguration(), "<script>\n\t" + sql + "</script>", null);
		MetaObject msObject = SystemMetaObject.forObject(statement);
        msObject.setValue("sqlSource", sqlSource);
        if (template.operType() == OperType.INSERT && StringUtil.hasText(ec.getKeyCol()) && ec.getPrimary().isUseGeneratedKeys()) 
            msObject.setValue("keyGenerator", new MultipleJdbc3KeyGenerator());
	}
	
	@SuppressWarnings("unchecked")
	private void _registerMethodMapper(MappedStatement statement, Class<?> mapperInterface, Method method, EntityClass entityClass) throws Exception { 
		Configuration configuration = statement.getConfiguration();
	    MapperRegistry registry = configuration.getMapperRegistry();
		Map<Class<?>, MapperProxyFactory<?>> map = (Map<Class<?>, MapperProxyFactory<?>>) konwnMappersField.get(registry);
		MapperProxyFactory<?> mf = map.get(mapperInterface);
		Map<Method, MapperMethod> mp = mf.getMethodCache();
		MapperMethod mm = mp.get(method);
		if (null == mm) {
			mm = new MapperMethod(mapperInterface, method, configuration);
			mp.put(method, mm);
		}
		MethodSignature ms = (MethodSignature) mapperMethod.get(mm);
		mapKey.set(ms, entityClass.getKeyField());
		returnMap.setBoolean(ms, true);
		ResultMap resultMap = statement.getResultMaps().get(0);
		resultMapType.set(resultMap, entityClass.getClazz());
	}
}
