package org.rapid.yeepay.response;

public class YPSmsCardBindResponse extends YPResponse {

	private static final long serialVersionUID = 3890857435881051193L;

	// 验证码:如果 codesender 为 YEEPAY，该字段为 ""
	private String smscode;
	// 实际短验发送方：CUSTOMER:商户；YEEPAY:易宝发送；BANK:银行发送
	private String codesender;
	// 支持的不从鉴权类型
	private String enhancedtype;
	// 实际短验发送类型
	private String smstype;

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

	public String getEnhancedtype() {
		return enhancedtype;
	}

	public void setEnhancedtype(String enhancedtype) {
		this.enhancedtype = enhancedtype;
	}

	public String getSmstype() {
		return smstype;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}
	
	// 订单状态：TO_VALIDATE:待短验；BIND_FAIL:绑卡失败；BIND_ERROR:绑卡异常；TO_ENHANCED:待补充鉴权；TIME_OUT:超时失败；FAIL:系统异常
	public boolean success() {
		return this.status.equals("TO_VALIDATE");
	}
}
