package org.rapid.yeepay.request;

public class YPSmsCardBindConfirmRequest extends YPRequest {

	private static final long serialVersionUID = -6050030828434725783L;
	
	// 验证码
	private String validatecode;

	public YPSmsCardBindConfirmRequest(String requestno) {
		super(requestno);
	}

	public String getValidatecode() {
		return validatecode;
	}
	
	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}
}
