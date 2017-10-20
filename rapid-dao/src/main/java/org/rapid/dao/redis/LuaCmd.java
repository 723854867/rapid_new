package org.rapid.dao.redis;

/**
 * Lua 脚本指令
 * 
 * @author Lynn
 */
public interface LuaCmd {

	/**
	 * lua 脚本需要的 key 数
	 * 
	 * @return
	 */
	int keyNum();
	
	/**
	 * 该指令对应的 lua 脚本内容
	 * 
	 * @return
	 */
	byte[] script();
}
