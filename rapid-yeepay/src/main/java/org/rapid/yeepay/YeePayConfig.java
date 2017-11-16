package org.rapid.yeepay;

/**
 * 易宝配置类
 * 
 * @author admin
 */
public class YeePayConfig {
	
	// 商户公钥
	private static String publicKey;
	// 商户私钥
	private static String privateKey;
	// 易宝公钥
	private static String ypPublicKey;
	// 易宝商户id
	private static String merchantNo;
	
	// 有短验绑卡
	private static String urlCardBindWithSms = "https://jinrong.yeepay.com/tzt-api/api/bindcard/request";
	// 有短验绑卡确认
	private static String urlCardBindWithSmsConfirm = "https://jinrong.yeepay.com/tzt-api/api/bindcard/confirm";
	// 绑卡短验重发
	private static String urlCardBindWithSmsRepeat = "https://jinrong.yeepay.com/tzt-api/api/bindcard/resendsms";
	// 解绑卡
	private static String urlCardUnbind = "https://jinrong.yeepay.com/tzt-api/api/unbind/request";
	// 短验充值
	private static String urlRechargeWithSms = "https://jinrong.yeepay.com/tzt-api/api/bindpay/request";
	// 短验充值确认
	private static String urlRechargeWithSmsConfirm = "https://jinrong.yeepay.com/tzt-api/api/bindpay/confirm";
	// 短验充值重发
	private static String urlRechargeWithSmsRepeat = "https://jinrong.yeepay.com/tzt-api/api/bindpay/resendsms";
	// 获取银行基本信息
	private static String urlBankInfo = "https://jinrong.yeepay.com/tzt-api/api/bankcard/check";
	
	public static String getPublicKey() {
		return publicKey;
	}
	
	public static void setPublicKey(String publicKey) {
		YeePayConfig.publicKey = publicKey;
	}
	
	public static String getPrivateKey() {
		return privateKey;
	}
	
	public static void setPrivateKey(String privateKey) {
		YeePayConfig.privateKey = privateKey;
	}
	
	public static String getYpPublicKey() {
		return ypPublicKey;
	}
	
	public static void setYpPublicKey(String ypPublicKey) {
		YeePayConfig.ypPublicKey = ypPublicKey;
	}
	
	public static String getMerchantNo() {
		return merchantNo;
	}
	
	public static void setMerchantNo(String merchantNo) {
		YeePayConfig.merchantNo = merchantNo;
	}
	
	public static String getUrlCardBindWithSms() {
		return urlCardBindWithSms;
	}
	
	public static void setUrlCardBindWithSms(String urlCardBindWithSms) {
		YeePayConfig.urlCardBindWithSms = urlCardBindWithSms;
	}
	
	public static String getUrlCardBindWithSmsConfirm() {
		return urlCardBindWithSmsConfirm;
	}
	
	public static void setUrlCardBindWithSmsConfirm(String urlCardBindWithSmsConfirm) {
		YeePayConfig.urlCardBindWithSmsConfirm = urlCardBindWithSmsConfirm;
	}
	
	public static String getUrlCardBindWithSmsRepeat() {
		return urlCardBindWithSmsRepeat;
	}
	
	public static void setUrlCardBindWithSmsRepeat(String urlCardBindWithSmsRepeat) {
		YeePayConfig.urlCardBindWithSmsRepeat = urlCardBindWithSmsRepeat;
	}
	
	public static String getUrlCardUnbind() {
		return urlCardUnbind;
	}
	
	public static void setUrlCardUnbind(String urlCardUnbind) {
		YeePayConfig.urlCardUnbind = urlCardUnbind;
	}
	
	public static String getUrlRechargeWithSms() {
		return urlRechargeWithSms;
	}
	
	public static void setUrlRechargeWithSms(String urlRechargeWithSms) {
		YeePayConfig.urlRechargeWithSms = urlRechargeWithSms;
	}
	
	public static String getUrlRechargeWithSmsConfirm() {
		return urlRechargeWithSmsConfirm;
	}
	
	public static void setUrlRechargeWithSmsConfirm(String urlRechargeWithSmsConfirm) {
		YeePayConfig.urlRechargeWithSmsConfirm = urlRechargeWithSmsConfirm;
	}
	
	public static String getUrlRechargeWithSmsRepeat() {
		return urlRechargeWithSmsRepeat;
	}
	
	public static void setUrlRechargeWithSmsRepeat(String urlRechargeWithSmsRepeat) {
		YeePayConfig.urlRechargeWithSmsRepeat = urlRechargeWithSmsRepeat;
	}
	
	public static String getUrlBankInfo() {
		return urlBankInfo;
	}
	
	public static void setUrlBankInfo(String urlBankInfo) {
		YeePayConfig.urlBankInfo = urlBankInfo;
	}
}
