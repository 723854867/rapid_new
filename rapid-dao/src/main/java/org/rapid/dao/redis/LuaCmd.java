package org.rapid.dao.redis;

public enum LuaCmd implements ILuaCmd {
	
	/**
	 * 获取验证码
	 */
	CAPTCHA_OBTAIN {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	/**
	 * 如果相等则删除，否则什么都不做
	 */
	DEL_IF_EQUALS {
		@Override
		public int keyNum() {
			return 1;
		}
	};
}
