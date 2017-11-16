package org.rapid.yeepay.request;

public class YPRechargeWithSmsRepeatRequest extends YPRequest {

	private static final long serialVersionUID = 6365055862683722437L;
	
	private String advicesmstype = "MESSAGE";

	public YPRechargeWithSmsRepeatRequest(String requestno) {
		super(requestno);
	}

	public String getAdvicesmstype() {
		return advicesmstype;
	}
	
	public void setAdvicesmstype(String advicesmstype) {
		this.advicesmstype = advicesmstype;
	}
}
