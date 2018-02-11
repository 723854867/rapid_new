package org.rapid.util.common.model.code;

import org.rapid.util.common.SpringContextUtil;
import org.rapid.util.common.bootstrap.Rapid;

public enum Code implements ICode {

	OK(0, "result.code.ok"),
	
	SYSTEM_ERROR(1, "result.code.system.error"),
	
	PARAM_ERROR(2, "result.code.param.error"),
	
	UPLOAD_SIZE_EXCEEDED(3, "result.code.upload.size.exceeded"),
	
	UNSUPPORTED_HTTP_METHOD(4, "result.code.unsupported.http.method"),
	
	UNSUPPORTED_HTTP_CONTENT_TYPE(5, "result.code.unsupported.http.content.type");;
	
	private int value;
	private String desc;
	
	private Code(int value, String code) {
		this.value = value;
		this.desc = SpringContextUtil.getBean("rapid", Rapid.class).language().get(code);
	}
	
	@Override
	public Integer id() {
		return this.value;
	}
	
	@Override
	public String desc() {
		return this.desc; 
	}
}
