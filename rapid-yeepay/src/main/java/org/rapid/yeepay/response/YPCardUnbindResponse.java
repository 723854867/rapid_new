package org.rapid.yeepay.response;

public class YPCardUnbindResponse extends YPResponse {

	private static final long serialVersionUID = 2665383755456538770L;

	// 用户标识
	private String identityid;
	// 用户标识类型
	private String identitytype;
	// 卡号前六位
	private String cardtop;
	// 卡号后四位
	private String cardlast;

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
	
	// SUCCESS-成功；FAIL-失败
	public boolean success() {
		return this.status.equals("SUCCESS");
	}
}
