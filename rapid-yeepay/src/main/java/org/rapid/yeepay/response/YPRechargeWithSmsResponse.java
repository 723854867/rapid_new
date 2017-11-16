package org.rapid.yeepay.response;

public class YPRechargeWithSmsResponse extends YPResponse {

	private static final long serialVersionUID = -8655898012269779810L;

	// 商户发短验时返回的短验码
	private String smscode;
	// YEEPAY-易宝；CUSTOMER-商户；BANK-银行
	private String codesender;
	// 实际短验发送类型：VOICE-语音；MESSAGE-短信
	private String smstype;
	// 空置，央行规定禁止返回敏感信息
	private String phone;
	private String free1;
	private String free2;
	private String free3;

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}

	public String getCodesender() {
		return codesender;
	}

	public void setCodesender(String codesender) {
		this.codesender = codesender;
	}

	public String getSmstype() {
		return smstype;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFree1() {
		return free1;
	}

	public void setFree1(String free1) {
		this.free1 = free1;
	}

	public String getFree2() {
		return free2;
	}

	public void setFree2(String free2) {
		this.free2 = free2;
	}

	public String getFree3() {
		return free3;
	}

	public void setFree3(String free3) {
		this.free3 = free3;
	}
	
	// TO_VALIDATE：待短验确认 PAY_FAIL：支付失败 FAIL：系统异常 
	public boolean success() { 
		return this.status.equals("TO_VALIDATE");
	}
}
