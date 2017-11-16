package org.rapid.yeepay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.rapid.util.common.serializer.GsonSerializer;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.common.serializer.Serializer;
import org.rapid.util.net.OKHttp;
import org.rapid.yeepay.request.YPBankInfoRequest;
import org.rapid.yeepay.request.YPCardUnbindRequest;
import org.rapid.yeepay.request.YPRechargeWithSmsConfirmRequest;
import org.rapid.yeepay.request.YPRechargeWithSmsRepeatRequest;
import org.rapid.yeepay.request.YPRechargeWithSmsRequest;
import org.rapid.yeepay.request.YPSmsCardBindConfirmRequest;
import org.rapid.yeepay.request.YPSmsCardBindRepeatRquest;
import org.rapid.yeepay.request.YPSmsCardBindRequest;
import org.rapid.yeepay.request.YPWithdrawRequest;
import org.rapid.yeepay.response.YPBankInfoResponse;
import org.rapid.yeepay.response.YPCardUnbindResponse;
import org.rapid.yeepay.response.YPEncryptResponse;
import org.rapid.yeepay.response.YPRechargeWithSmsConfirmResponse;
import org.rapid.yeepay.response.YPRechargeWithSmsRepeatResponse;
import org.rapid.yeepay.response.YPRechargeWithSmsResponse;
import org.rapid.yeepay.response.YPSmsCardBindConfirmResponse;
import org.rapid.yeepay.response.YPSmsCardBindRepeatResponse;
import org.rapid.yeepay.response.YPSmsCardBindResponse;
import org.rapid.yeepay.response.YPWithdrawResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YeePay {
	
	private static final Logger logger = LoggerFactory.getLogger(YeePay.class);

	private OKHttp okHttp;
	private Serializer serializer = new GsonSerializer();
	
	/**
	 * 有短验绑卡请求
	 * 
	 * @param identityId 用户唯一标识
	 * @param name 真实姓名
	 * @param idNo 身份证号
	 * @param bankNo 银行卡号
	 * @param mobile 预留手机
	 * @return
	 * @throws Exception
	 */
	public YPSmsCardBindResponse cardBindWithSms(String identityId, String name, String idNo, String bankNo, String mobile) throws Exception { 
		YPSmsCardBindRequest request = new YPSmsCardBindRequest();
		request.setCardno(bankNo);
		request.setIdcardno(idNo);
		request.setUsername(name);
		request.setPhone(mobile);
		request.setIdentityid(identityId);
		Map<String, Object> params = request.sign();
		logger.info("易宝短验绑卡请求：{}", params);
		YPSmsCardBindResponse response = this.okHttp.postForm(YeePayConfig.getUrlCardBindWithSms(), params, serializer, YPEncryptResponse.class).verify(YPSmsCardBindResponse.class);
		logger.info("易宝短验绑卡响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 易宝短验绑卡确认请求
	 * 
	 * @param requestno {@link cardBindWithSms()} 中返回的 requestno
	 * @return
	 * @throws Exception
	 */
	public YPSmsCardBindConfirmResponse cardBindWithSmsConfirm(String requestno, String code) throws Exception {
		YPSmsCardBindConfirmRequest request = new YPSmsCardBindConfirmRequest(requestno);
		request.setValidatecode(code);
		Map<String, Object> params = request.sign();
		logger.info("易宝短验绑卡确认请求：{}", params);
		YPSmsCardBindConfirmResponse response = this.okHttp.postForm(YeePayConfig.getUrlCardBindWithSmsConfirm(), params, serializer, YPEncryptResponse.class).verify(YPSmsCardBindConfirmResponse.class);
		logger.info("易宝短验绑卡确认响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 有短验绑卡重发短验
	 * 
	 * @param requestno
	 * @return
	 * @throws Exception
	 */
	public YPSmsCardBindRepeatResponse cardBindWithSmsRepeat(String requestno) throws Exception { 
		YPSmsCardBindRepeatRquest request = new YPSmsCardBindRepeatRquest(requestno);
		Map<String, Object> params = request.sign();
		logger.info("易宝短验绑卡重发请求：{}", params);
		YPSmsCardBindRepeatResponse response = this.okHttp.postForm(YeePayConfig.getUrlCardBindWithSmsRepeat(), params, serializer, YPEncryptResponse.class).verify(YPSmsCardBindRepeatResponse.class);
		logger.info("易宝短验绑卡重发响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 解绑卡
	 * 
	 * @param identityid 用户唯一标识号
	 * @param cardtop 卡号前六位
	 * @param cardlast 卡号后四位
	 * @return
	 * @throws Exception
	 */
	public YPCardUnbindResponse cardUnbind(String identityid, String cardtop, String cardlast) throws Exception {
		YPCardUnbindRequest request = new YPCardUnbindRequest();
		request.setCardtop(cardtop);
		request.setCardlast(cardlast);
		request.setIdentityid(identityid);
		Map<String, Object> params = request.sign();
		logger.info("易宝解绑卡请求：{}", params);
		YPCardUnbindResponse response = this.okHttp.postForm(YeePayConfig.getUrlCardUnbind(), params, serializer, YPEncryptResponse.class).verify(YPCardUnbindResponse.class);
		logger.info("易宝解绑卡响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 有短验充值
	 * 
	 * @param identityid 用户唯一标识
	 * @param bankNo 银行卡号
	 * @param money 充值金额
	 * @param registtime 用户注册时间
	 * @param terminalid 注册硬件终端标识
	 * @param lastloginterminalid 上次登录硬件终端标识
	 * @param productname 产品名称
	 * @param callbackurl 回调地址
	 * @param issetpaypwd 是否设置支付密码
	 * @return
	 * @throws Exception
	 */
	public YPRechargeWithSmsResponse rechargeWithSms(String identityid, String bankNo, BigDecimal money, String registtime, String terminalid, String lastloginterminalid, String productname, String callbackurl, boolean issetpaypwd) throws Exception {
		YPRechargeWithSmsRequest request = new YPRechargeWithSmsRequest();
		request.setIdentityid(identityid);
		request.setCardtop(bankNo.substring(0, 6));
		request.setCardlast(bankNo.substring(bankNo.length() - 4));
		request.setAmount(money.setScale(2, RoundingMode.FLOOR).toString());
		request.setRegisttime(registtime);
		request.setTerminalid(terminalid);
		request.setLastloginterminalid(lastloginterminalid);
		request.setProductname(productname);
		request.setCallbackurl(callbackurl);
		request.setIssetpaypwd(issetpaypwd ? "1" : "0");
		Map<String, Object> params = request.sign();
		logger.info("易宝有短验充值请求：{}", params);
		YPRechargeWithSmsResponse response = this.okHttp.postForm(YeePayConfig.getUrlRechargeWithSms(), params, serializer, YPEncryptResponse.class).verify(YPRechargeWithSmsResponse.class);
		logger.info("易宝有短验充值响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 有短验充值确认
	 * 
	 * @param requestno 订单号
	 * @param code 验证码
	 * @return
	 * @throws Exception
	 */
	public YPRechargeWithSmsConfirmResponse rechargeWithSmsConfirm(String requestno, String code) throws Exception {
		YPRechargeWithSmsConfirmRequest request = new YPRechargeWithSmsConfirmRequest(requestno);
		request.setValidatecode(code);
		Map<String, Object> params = request.sign();
		logger.info("易宝有短验充值确认请求：{}", params);
		YPRechargeWithSmsConfirmResponse response = this.okHttp.postForm(YeePayConfig.getUrlRechargeWithSmsConfirm(), params, serializer, YPEncryptResponse.class).verify(YPRechargeWithSmsConfirmResponse.class);
		logger.info("易宝有短验充值确认响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 有短验充值重发短验
	 * 
	 * @param requestno
	 * @return
	 * @throws Exception
	 */
	public YPRechargeWithSmsRepeatResponse rechargeWithSmsRepeat(String requestno) throws Exception {
		YPRechargeWithSmsRepeatRequest request = new YPRechargeWithSmsRepeatRequest(requestno);
		Map<String, Object> params = request.sign();
		logger.info("易宝有短验充值短验重发请求：{}", params);
		YPRechargeWithSmsRepeatResponse response = this.okHttp.postForm(YeePayConfig.getUrlRechargeWithSmsRepeat(), params, serializer, YPEncryptResponse.class).verify(YPRechargeWithSmsRepeatResponse.class);
		logger.info("易宝有短验充值短验重发响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 提现
	 * 
	 * @param identityid 用户唯一标识
	 * @param bankNo 银行卡号
	 * @param money 提现金额
	 * @param ip 用户当前ip
	 * @param callback 提现回调地址
	 * @return
	 * @throws Exception
	 */
	public YPWithdrawResponse withdraw(String identityid, String bankNo, BigDecimal money, String ip, String callback) throws Exception {
		YPWithdrawRequest request = new YPWithdrawRequest();
		request.setIdentityid(identityid);
		request.setCardtop(bankNo.substring(0, 6));
		request.setCardlast(bankNo.substring(bankNo.length() - 4));
		request.setAmount(money.setScale(2, RoundingMode.FLOOR));
		request.setUserip(ip);
		request.setCallbackurl(callback);
		Map<String, Object> params = request.sign();
		logger.info("易宝有短验充值短验重发请求：{}", params);
		YPWithdrawResponse response = this.okHttp.postForm(YeePayConfig.getUrlRechargeWithSmsRepeat(), params, serializer, YPEncryptResponse.class).verify(YPWithdrawResponse.class);
		logger.info("易宝有短验充值短验重发响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	/**
	 * 获取银行基本信息
	 * 
	 * @param bankNo
	 * @return
	 * @throws Exception 
	 */
	public YPBankInfoResponse bankInfo(String bankNo) throws Exception {
		YPBankInfoRequest request = new YPBankInfoRequest(bankNo);
		Map<String, Object> params = request.sign();
		logger.info("易宝获取银行基本信息请求：{}", params);
		YPBankInfoResponse response = this.okHttp.postForm(YeePayConfig.getUrlBankInfo(), params, serializer, YPEncryptResponse.class).verify(YPBankInfoResponse.class);
		logger.info("易宝获取银行基本信息响应：{}", SerializeUtil.GsonUtil.GSON.toJson(response));
		return response;
	}
	
	public void setOkHttp(OKHttp okHttp) {
		this.okHttp = okHttp;
	}
}
