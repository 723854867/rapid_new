package org.rapid.yeepay.request;

public class YPCardUnbindRequest extends YPRequest {

	private static final long serialVersionUID = 1532099360233811710L;

	// 用户标识
	private String identityid;
	// 用户标识类型
	private String identitytype = "PHONE";
	// 卡号前六位
	private String cardtop;
	// 卡号后四位
	private String cardlast;

	public YPCardUnbindRequest() {
		super(null);
	}

	public String getIdentityid() {
		return identityid;
	}

	public void setIdentityid(String identityid) {
		this.identityid = identityid;
	}

	public String getIdentitytype() {
		return identitytype;
	}

	public void setIdentitytype(String identitytype) {
		this.identitytype = identitytype;
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
}
