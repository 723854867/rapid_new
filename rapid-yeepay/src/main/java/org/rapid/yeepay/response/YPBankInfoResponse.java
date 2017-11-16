package org.rapid.yeepay.response;

public class YPBankInfoResponse extends YPResponse {

	private static final long serialVersionUID = 1619935849658163950L;

	// 卡号前六位
	private String cardtop;
	// 卡号后四位
	private String cardlast;
	// 银行编码
	private String bankcode;
	// 银行名称
	private String bankname;
	// 卡类别：DEBIT：借记卡 CREDIT：信用卡
	private String cardtype;
	// 是否有效：VALID：有效 INVALID：无效
	private String isvalid;

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

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
}
