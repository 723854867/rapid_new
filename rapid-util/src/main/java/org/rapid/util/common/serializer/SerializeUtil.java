package org.rapid.util.common.serializer;

import org.rapid.util.common.Consts;

import com.google.gson.Gson;

public interface SerializeUtil {
	
	class GsonUtil {
		public static final Gson GSON = new Gson();
	}

	class RedisUtil {
		public static final byte[] encode(Object value) {
			return (value instanceof byte[]) ? (byte[]) value : _encode(value.toString());
		}
		
		public static final byte[][] encode(Object... params) {
			byte[][] buffer = new byte[params.length][];
			for (int i = 0, len = params.length; i < len; i++) {
				if (params[i] instanceof byte[])
					buffer[i] = (byte[]) params[i];
				else
					buffer[i] = _encode(params[i].toString());
			}
			return buffer;
		}
		
		private static final byte[] _encode(String value) {
			return value.getBytes(Consts.UTF_8);
		}
	}
}
