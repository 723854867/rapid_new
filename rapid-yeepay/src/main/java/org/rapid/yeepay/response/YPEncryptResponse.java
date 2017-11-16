package org.rapid.yeepay.response;

import java.util.Map;
import java.util.Map.Entry;

import javax.exception.CryptException;

import java.util.TreeMap;

import org.rapid.util.common.codec.Algorithms;
import org.rapid.util.common.codec.Decrypt;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.yeepay.YeePayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YPEncryptResponse {
	
	private static final Logger logger = LoggerFactory.getLogger(YPEncryptResponse.class);

	private String data;
	private String encryptkey;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEncryptkey() {
		return encryptkey;
	}

	public void setEncryptkey(String encryptkey) {
		this.encryptkey = encryptkey;
	}
	
	/**
	 * 响应验证
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <RESPONSE extends YPResponse> RESPONSE verify(Class<RESPONSE> clazz) { 
		String decryptData = null;
		try {
			String aesKey = Decrypt.RSAWithBase64(encryptkey, Algorithms.RSA_ECB_PKCS1, YeePayConfig.getPrivateKey());
			decryptData = Decrypt.AESWithBase64(this.data, Algorithms.AES_ECB_PKCS5, aesKey);
			Map<String, Object> map = SerializeUtil.GsonUtil.GSON.fromJson(decryptData, TreeMap.class);
			String sign = map.remove("sign").toString();
			StringBuilder builder = new StringBuilder();
			for (Entry<String, Object> entry : map.entrySet()) 
				builder.append(entry.getValue().toString());
			if(!Decrypt.RSASignWithBase64(builder.toString(), sign, Algorithms.SIGN_SHA1WITHRSA, YeePayConfig.getYpPublicKey()))
				throw new RuntimeException("易宝延签失败!");
		} catch (CryptException e) {
			logger.error("易宝响应验签解密失败：data = {}, encryptkey = {}", this.data, this.encryptkey, e);
			return null;
		}
		
		return SerializeUtil.GsonUtil.GSON.fromJson(decryptData, clazz);
	}
}
