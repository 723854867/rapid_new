package org.rapid.util.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.rapid.util.lang.StringUtil;

public class CollectionUtil {

	public static final boolean isEmpty(Map<?, ?> map) { 
		return null == map || map.isEmpty();
	}
	
	public static final boolean isEmpty(Collection<?> collection) { 
		return null == collection || collection.isEmpty();
	}
	
	public static final Set<String> splitIntoStringSet(String value, String regex) {
		Set<String> set = new HashSet<String>();
		if (StringUtil.hasText(value)) {
			String[] arr = value.split(regex);
			for (String string : arr)
				set.add(string);
		}
		return set;
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> Set<T> toSet(Object... params) {
		Set<T> set = new HashSet<T>();
		for (Object object : params)
			set.add((T) object);
		return set;
	}
}
