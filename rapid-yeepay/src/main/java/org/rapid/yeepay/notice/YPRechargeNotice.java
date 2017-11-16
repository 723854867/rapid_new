package org.rapid.yeepay.notice;

import java.math.BigDecimal;

import org.rapid.yeepay.response.YPResponse;

public class YPRechargeNotice extends YPResponse {

	private static final long serialVersionUID = -3345614055470349896L;

	// 金额
	private BigDecimal amount;
	// 卡号前六位
	private String cardtop;
	// 卡号后四位
	private String cardlast;
	// 银行编码
	private String bankcode;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCardtop() {
		return cardtop;
	}

	public void setCardtop(String cardtop) {
		this.cardtop = cardtop;
	}

	public String getCardlast() {
		return cardlast;
	}

	public void setCardlast(String cardlast) {
		this.cardlast = cardlast;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	
	// PAY_SUCCESS：支付成功 PAY_FAIL：支付失败 TIME_OUT：超时失败 
	public boolean success() {
		return this.status.equals("PAY_SUCCESS");
	}
}
