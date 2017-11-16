package org.rapid.yeepay.response;

import java.math.BigDecimal;

public class YPRechargeWithSmsRepeatResponse extends YPResponse {

	private static final long serialVersionUID = 6843875365967420171L;
 
	// 商户短验时返回的短验码
	private String smscode;
	// 短验发送方
	private String codesender;
	// 金额
	private BigDecimal amount;
	
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
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	// TO_VALIDATE：待短验确认 PAY_FAIL：支付失败 TIME_OUT：超时失败 FAIL：系统异常
	public boolean success() { 
		return this.status.equals("TO_VALIDATE");
	}
}
