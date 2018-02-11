package org.rapid.util.common;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.rapid.util.lang.StringUtil;

/**
 * 语言类
 * 
 * @author lynn
 */
public class Language {
	
	private Map<String, String> languages = new HashMap<String, String>();
	
	public void load(Properties properties) {
		for (Entry<Object, Object> entry : properties.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			if (!StringUtil.hasText(key) || !StringUtil.hasText(value))
				continue;
			languages.put(key.trim(), value.trim());
		}
	}
	
	public String get(String code) {
		String word = languages.get(code);
		return null == word ? StringUtil.EMPTY : word;
	}
	
	public String getAndFormat(String code, Object... formats) {
		String word = get(code);
		return MessageFormat.format(word, formats);
	}
}
