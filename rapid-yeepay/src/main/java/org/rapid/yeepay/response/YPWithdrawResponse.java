package org.rapid.yeepay.response;

import java.math.BigDecimal;

public class YPWithdrawResponse extends YPResponse {

	private static final long serialVersionUID = 7246110179693224140L;

	private BigDecimal amount;
	private String cardtop;
	private String cardlast;
	private String bankcode;
	private String free1;
	private String free2;
	private String free3;

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
	
	// PROCESSING：处理中 WITHDRAW_FAIL：提现失败 FAIL：系统异常 
	public boolean success() { 
		return this.status.equals("PROCESSING");
	}
}
