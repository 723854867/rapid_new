package org.rapid.yeepay.request;

import org.rapid.util.common.KeyUtil;
import org.rapid.util.lang.TimeUtil;

public class YPRechargeWithSmsRequest extends YPRequest {

	private static final long serialVersionUID = 2321476789258256506L;
	
	// 用户唯一标识
	private String identityid;
	// 用户标识类型
	private String identitytype = "PHONE";
	// 卡号前六位
	private String cardtop;
	// 卡号后四位
	private String cardlast;
	// 充值金额:单位：元 精确到两位小数 大于等于 0.01
	private String amount;
	// 建议短验发送类型
	private String advicesmstype = "MESSAGE";
	// 有效期
	private String avaliabletime;
	// 商品名称
	private String productname;
	// 回调地址（可选）
	private String callbackurl;
	// 请求时间
	private String requesttime;
	// 注册硬件终端标识码
	private String terminalid;
	// 注册时间
	private String registtime;
	// 注册ip（可选）
	private String registip;
	// 上次登录ip（可选）
	private String lastloginip;
	// 上次登录时间（可选）
	private String lastlogintime;
	// 上次登录硬件终端标识码
	private String lastloginterminalid;
	// 是否设置支付密码
	private String issetpaypwd;
	private String free1;
	private String free2;
	private String free3;

	public YPRechargeWithSmsRequest() {
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAdvicesmstype() {
		return advicesmstype;
	}

	public void setAdvicesmstype(String advicesmstype) {
		this.advicesmstype = advicesmstype;
	}

	public String getAvaliabletime() {
		return avaliabletime;
	}

	public void setAvaliabletime(String avaliabletime) {
		this.avaliabletime = avaliabletime;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getRegisttime() {
		return registtime;
	}

	public void setRegisttime(String registtime) {
		this.registtime = registtime;
	}

	public String getRegistip() {
		return registip;
	}

	public void setRegistip(String registip) {
		this.registip = registip;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginterminalid() {
		return lastloginterminalid;
	}

	public void setLastloginterminalid(String lastloginterminalid) {
		this.lastloginterminalid = lastloginterminalid;
	}

	public String getIssetpaypwd() {
		return issetpaypwd;
	}

	public void setIssetpaypwd(String issetpaypwd) {
		this.issetpaypwd = issetpaypwd;
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
