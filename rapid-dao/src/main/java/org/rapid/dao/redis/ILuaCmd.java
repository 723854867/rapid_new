package org.rapid.dao.redis;

/**
 * Lua 脚本指令
 * 
 * @author Lynn
 */
public interface ILuaCmd {

	/**
	 * lua 脚本需要的 key 数
	 * 
	 * @return
	 */
	int keyNum();
}
