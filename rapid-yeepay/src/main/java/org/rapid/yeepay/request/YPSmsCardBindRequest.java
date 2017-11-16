package org.rapid.yeepay.request;

import org.rapid.util.common.KeyUtil;
import org.rapid.util.lang.TimeUtil;

/**
 * 短验绑卡请求
 * 
 * @author lynn
 */
public class YPSmsCardBindRequest extends YPRequest {

	private static final long serialVersionUID = -8885511875460369829L;

	// 用户标识类型
	private String identityid;
	// 用户标识类型
	private String identitytype = "PHONE";
	// 卡号
	private String cardno;
	// 证件号
	private String idcardno;
	// 证件类型
	private String idcardtype = "ID";
	// 用户姓名：只支持中文
	private String username;
	// 手机号
	private String phone;
	// 建议短验发送类型：MESSAGE 和 VOICE
	private String advicesmstype = "MESSAGE";
	// 补充鉴权类型
	private String customerenhancedtype = "AUTH_REMIT";
	// 绑卡订单有效期:单位分钟，默认24h；大于1min小于48h
	private String avaliabletime;
	// 超时回调地址
	private String callbackurl;
	// 请求时间:yyyy-MM-dd HH:mm:ss
	private String requesttime;

	/**
	 * 请求号 requestno 自动生成
	 * 
	 * @param merchantno
	 */
	public YPSmsCardBindRequest() {
		super(KeyUtil.warterNo());
		this.requesttime = TimeUtil.getDate(TimeUtil.YYYY_MM_DD_HH_MM_SS);
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

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getIdcardtype() {
		return idcardtype;
	}

	public void setIdcardtype(String idcardtype) {
		this.idcardtype = idcardtype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdvicesmstype() {
		return advicesmstype;
	}

	public void setAdvicesmstype(String advicesmstype) {
		this.advicesmstype = advicesmstype;
	}

	public String getCustomerenhancedtype() {
		return customerenhancedtype;
	}

	public void setCustomerenhancedtype(String customerenhancedtype) {
		this.customerenhancedtype = customerenhancedtype;
	}

	public String getAvaliabletime() {
		return avaliabletime;
	}

	public void setAvaliabletime(String avaliabletime) {
		this.avaliabletime = avaliabletime;
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
}
