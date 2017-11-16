package org.rapid.yeepay.request;

public class YPSmsCardBindRepeatRquest extends YPRequest {

	private static final long serialVersionUID = -7672003421217585544L;
	
	private String advicesmstype = "MESSAGE";

	public YPSmsCardBindRepeatRquest(String requestno) {
		super(requestno);
	}

	public void setAdvicesmstype(String advicesmstype) {
		this.advicesmstype = advicesmstype;
	}
	
	public String getAdvicesmstype() {
		return advicesmstype;
	}
}
