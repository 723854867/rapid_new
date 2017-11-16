package org.rapid.util.common.serializer;

import java.nio.charset.Charset;

import org.rapid.util.common.Consts;

public class GsonSerializer implements Serializer {
	
	private Charset charset = Consts.UTF_8;

	@Override
	public byte[] serial(Object object) {
		String value = SerializeUtil.GsonUtil.GSON.toJson(object);
		return value.getBytes(charset);
	}

	@Override
	public <ENTITY> ENTITY deserial(byte[] buffer, Class<ENTITY> clazz) {
		String value = new String(buffer, charset);
		return SerializeUtil.GsonUtil.GSON.fromJson(value, clazz);
	}
	
	public void setCharset(String encoding) {
		this.charset = Charset.forName(encoding);
	}
}
