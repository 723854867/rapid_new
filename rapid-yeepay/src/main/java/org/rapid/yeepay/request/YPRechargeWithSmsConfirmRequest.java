package org.rapid.yeepay.request;

public class YPRechargeWithSmsConfirmRequest extends YPRequest {

	private static final long serialVersionUID = -8864189793944063570L;
	
	private String validatecode;

	public YPRechargeWithSmsConfirmRequest(String requestno) {
		super(requestno);
	}
	
	public String getValidatecode() {
		return validatecode;
	}
	
	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}
}
