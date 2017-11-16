package org.rapid.yeepay.response;

public class YPSmsCardBindConfirmResponse extends YPResponse {

	private static final long serialVersionUID = -3263867455232710298L;

	// 银行编码
	private String bankcode;
	// 卡号前六位
	private String cardtop;
	// 卡号后四位
	private String cardlast;

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
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

	// 订单状态：BIND_SUCCESS-绑卡成功；BIND_FAIL-绑卡失败；BIND_ERROR-绑卡异常(可重试)；TO_VALIDATE-待短验；TIME_OUT-超时失败；FAIL-系统异常
	public boolean success() {
		return this.status.equals("BIND_SUCCESS");
	}
}
