package org.rapid.yeepay.response;

import java.math.BigDecimal;

public class YPRechargeWithSmsConfirmResponse extends YPResponse {

	private static final long serialVersionUID = 7863409696323241451L;

	// 充值金额
	private BigDecimal amount;
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	// TO_VALIDATE：待短验确认 PAY_FAIL：支付失败 PROCESSING：处理中 TIME_OUT：超时失败 FAIL：系统异常 
	public boolean success() { 
		return this.status.equals("PROCESSING");
	}
}
