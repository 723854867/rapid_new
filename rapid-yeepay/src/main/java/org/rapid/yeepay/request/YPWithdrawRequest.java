package org.rapid.yeepay.request;

import java.math.BigDecimal;

import org.rapid.util.common.KeyUtil;
import org.rapid.util.lang.TimeUtil;

public class YPWithdrawRequest extends YPRequest {

	private static final long serialVersionUID = -5761822711422076869L;

	// 用户标识
	private String identityid;
	// 用户标识类型：MAC：网卡地址 IMEI：国际移动设备标识 ID_CARD：用户身份证号 PHONE：手机号 EMAIL：邮箱 USER_ID：用户 id
	// AGREEMENT_NO：用户纸质订单协议 号
	private String identitytype = "PHONE";
	// 卡号前六位
	private String cardtop;
	// 卡号后四位
	private String cardlast;
	// 提现金额：单位：元 精确到两位小数 大于等于 0.01
	private BigDecimal amount;
	// 币种：固定值 156：人民币
	private String currency = "156";
	// 提现类型：NATRALDAY_NORMAL：自然日 t+1 NATRALDAY_URGENT：自然日 t+0
	private String drawtype = "NATRALDAY_URGENT";
	// 回调地址
	private String callbackurl;
	// 用户ip
	private String userip;
	// 请求时间
	private String requesttime;
	private String free1;
	private String free2;
	private String free3;

	public YPWithdrawRequest() {
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDrawtype() {
		return drawtype;
	}

	public void setDrawtype(String drawtype) {
		this.drawtype = drawtype;
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
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
}
