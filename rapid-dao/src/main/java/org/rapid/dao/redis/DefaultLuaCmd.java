package org.rapid.dao.redis;

enum DefaultLuaCmd implements LuaCmd {
	
	/**
	 * 获取验证码
	 */
	CAPTCHA_OBTAIN,
	
	/**
	 * 如果相等则删除，否则什么都不做
	 */
	DEL_IF_EQUALS;

	@Override
	public int keyNum() {
		return 0;
	}

	@Override
	public byte[] script() {
		return null;
	}
}
