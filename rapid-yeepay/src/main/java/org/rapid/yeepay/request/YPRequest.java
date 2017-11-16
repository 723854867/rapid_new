package org.rapid.yeepay.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.rapid.util.common.KeyUtil;
import org.rapid.util.common.codec.Algorithms;
import org.rapid.util.common.codec.Encrypt;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.reflect.BeanUtil;
import org.rapid.yeepay.YeePayConfig;

public abstract class YPRequest implements Serializable {

	private static final long serialVersionUID = 2868975696091803702L;
	
	protected String requestno;
	protected String merchantno;
	
	protected YPRequest(String requestno) {
		this.requestno = requestno;
		this.merchantno = YeePayConfig.getMerchantNo();
	}

	public String getRequestno() {
		return requestno;
	}

	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}

	public String getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(String merchantno) {
		this.merchantno = merchantno;
	}
	
	/**
	 * 签名并且生成请求参数
	 * 
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> sign() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = BeanUtil.beanToTreeMap(this, false);
		StringBuilder builder = new StringBuilder();
		for (Entry<String, Object> entry : params.entrySet())
			builder.append(entry.getValue().toString());
		
		String value = builder.toString();
		String sign = StringUtil.hasLength(value) ? Encrypt.RSASignWithBase64(value, Algorithms.SIGN_SHA1WITHRSA, YeePayConfig.getPrivateKey()) : StringUtil.EMPTY;
		params.put("sign", sign);
		String aesKey = KeyUtil.code(16);
		String data = Encrypt.AESwithBase64(SerializeUtil.GsonUtil.GSON.toJson(params), Algorithms.AES_ECB_PKCS5, aesKey);
		String encryptAesKey = Encrypt.RSAWithBase64(aesKey, Algorithms.RSA_ECB_PKCS1, YeePayConfig.getYpPublicKey());
		map.put("merchantno", this.merchantno);
		map.put("data", data);
		map.put("encryptkey", encryptAesKey);
		return map;
	}
}
