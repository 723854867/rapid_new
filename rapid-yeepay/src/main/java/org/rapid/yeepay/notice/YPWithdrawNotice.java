package org.rapid.yeepay.notice;

import java.math.BigDecimal;

import org.rapid.yeepay.response.YPResponse;

public class YPWithdrawNotice extends YPResponse {

	private static final long serialVersionUID = -608669758227423840L;

	// 提现金额
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
	
	// WITHDRAW_SUCCESS：提现成功 WITHDRAW_FAIL：提现失败 
	public boolean success() {
		return this.status.equals("WITHDRAW_SUCCESS");
	}
}
