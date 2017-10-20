package org.rapid.util.common.model.code;

import org.rapid.util.common.Language;

public enum Code implements ICode {

	OK(0, "result.code.ok"),
	
	SYSTEM_ERROR(1, "result.code.system.error"),
	
	PARAM_ERROR(2, "result.code.param.error"),
	
	UPLOAD_SIZE_EXCEEDED(3, "result.code.upload.size.exceeded"),
	
	UNSUPPORTED_HTTP_METHOD(4, "result.code.unsupported.http.method");
	
	private int value;
	private String desc;
	
	private Code(int value, String langKey) {
		this.value = value;
		this.desc = Language.get(langKey);
	}
	
	@Override
	public Integer getId() {
		return this.value;
	}
	
	@Override
	public String desc() {
		return this.desc;
	}
}
