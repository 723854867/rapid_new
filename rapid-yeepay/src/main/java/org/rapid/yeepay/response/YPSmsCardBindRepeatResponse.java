package org.rapid.yeepay.response;

public class YPSmsCardBindRepeatResponse extends YPResponse {

	private static final long serialVersionUID = 8470238005762108641L;

	// 商户发短验时返回的易宝生成的短验码
	private String smscode;
	// CUSTOMER-商户发送；YEEPAY-易宝发送；BANK-银行发送
	private String codesender;

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
	
	// 订单状态：TO_VALIDATE:待短验；TIME_OUT-超时失败；FAIL-系统异常
	public boolean success() { 
		return this.status.equals("TO_VALIDATE");
	}
}
