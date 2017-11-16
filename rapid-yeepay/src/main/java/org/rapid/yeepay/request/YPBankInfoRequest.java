package org.rapid.yeepay.request;

public class YPBankInfoRequest extends YPRequest {

	private static final long serialVersionUID = -5025223337458890496L;
	
	private String cardno;

	public YPBankInfoRequest(String cardno) {
		super(null);
		this.cardno = cardno;
	}
	
	public String getCardno() {
		return cardno;
	}
	
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
}
