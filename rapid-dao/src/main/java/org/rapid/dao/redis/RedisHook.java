package org.rapid.dao.redis;

import java.io.IOException;

import org.rapid.util.common.bootstrap.hook.InitialHook;
import org.rapid.util.common.model.enums.FileType;
import org.rapid.util.io.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisHook implements InitialHook {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisHook.class);
	
	@Autowired(required = false)
	private LuaFiber luaFiber;

	@Override
	public void execute() {
		if (null != luaFiber) {
			String prefix = "/conf/lua/";
			for (LuaCmd cmd : LuaCmd.values()) {
				byte[] buffer = null;
				try {
					buffer = ResourceUtil.readBuffer(RedisHook.class, prefix + cmd.name().toLowerCase() + FileType.LUA.suffix());
				} catch (IOException e) {
					logger.error("System lua script load failure, system will closed...", e);
					System.exit(1);
				}
				luaFiber.addScript(cmd, buffer);
			}
		}
	}
}
