package org.rapid.yeepay.response;

import java.io.Serializable;

public class YPResponse implements Serializable {

	private static final long serialVersionUID = 7662226204373446549L;

	// 易宝流水号
	protected String yborderid;
	// 商户id
	protected String merchantno;
	// 请求号
	protected String requestno;
	// 错误码
	protected String errorcode;
	// 错误信息
	protected String errormsg;
	// 签名
	protected String sign;
	protected String status;
	
	public String getYborderid() {
		return yborderid;
	}
	
	public void setYborderid(String yborderid) {
		this.yborderid = yborderid;
	}

	public String getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(String merchantno) {
		this.merchantno = merchantno;
	}

	public String getRequestno() {
		return requestno;
	}

	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
