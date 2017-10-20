package org.rapid.dao.redis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.rapid.util.common.model.enums.FileType;
import org.rapid.util.io.ResourceUtil;

public class DefaultLuaScriptLoader implements LuaScriptLoader {

	@Override
	public Map<LuaCmd, byte[]> load() throws IOException {
		String prefix = "/conf/lua/";
		Map<LuaCmd, byte[]> buffers = new HashMap<LuaCmd, byte[]>();
		for (DefaultLuaCmd cmd : DefaultLuaCmd.values()) {
			byte[] buffer = ResourceUtil.read(DefaultLuaScriptLoader.class, prefix + cmd.name().toLowerCase() + FileType.LUA.suffix());
			buffers.put(cmd, buffer);
		}
		return buffers;
	}
}
