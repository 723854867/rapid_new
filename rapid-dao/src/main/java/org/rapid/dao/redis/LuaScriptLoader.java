package org.rapid.dao.redis;

import java.io.IOException;
import java.util.Map;

/**
 * lua 脚本加载 SPI 接口
 * 
 * @author lynn
 */
public interface LuaScriptLoader {

	/**
	 * 加载脚本
	 * 
	 * @param scripts
	 */
	Map<LuaCmd, byte[]> load() throws IOException;
}
