package org.rapid.util.common.serializer;

public interface Serializer {

	byte[] serial(Object object);
	
	<ENTITY> ENTITY deserial(byte[] buffer, Class<ENTITY> clazz);
}
