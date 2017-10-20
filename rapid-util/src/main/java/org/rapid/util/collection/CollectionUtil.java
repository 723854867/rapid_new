package org.rapid.util.collection;

import java.util.Map;

public class CollectionUtil {

	public static final boolean isEmpty(Map<?, ?> map) { 
		return null == map || map.isEmpty();
	}
}
