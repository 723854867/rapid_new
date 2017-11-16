package org.rapid.util.common.codec;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.exception.CryptException;

import org.apache.commons.codec.binary.Base64;
import org.rapid.util.common.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Decrypt {
	
	private static final Logger logger = LoggerFactory.getLogger(Decrypt.class);
	
	/**
	 * 使用公钥对用私钥签名的数据进行验签
	 * 
	 * @param content 代签数据
	 * @param sign BASE64 编码过的 签名
	 * @param signAlgorithm 验签算法
	 * @param publicKey BASE64 编码过得 公钥
	 * @return
	 * @throws CryptException
	 */
	public static final boolean RSASignWithBase64(String content, String sign, String signAlgorithm, String publicKey) throws CryptException {
		return RSASign(content, Base64.decodeBase64(sign), signAlgorithm, publicKey);
	}
	
	/**
	 * 使用公钥对用私钥签名的数据进行验签
	 * 
	 * @param content
	 * @param sign
	 * @param signAlgorithm
	 * @param publicKey
	 * @return
	 * @throws CryptException
	 */
	public static final boolean RSASign(String content, byte[] sign, String signAlgorithm, String publicKey) throws CryptException {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(Algorithms.RSA);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));
			Signature signature = Signature.getInstance(signAlgorithm);
			signature.initVerify(pubKey);
			signature.update(content.getBytes(Consts.UTF_8));
			return signature.verify(sign);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | SignatureException e) {
			logger.error("RSA 验签失败：验签内容 - {}， 签名 - {}， 验签算法 - {}， 公钥 - {}", content, sign, signAlgorithm, publicKey);
			throw new CryptException("RSA 验签失败", e);
		}
	}

	/**
	 * RSA 私钥解密
	 * 
	 * @param content BASE64 编码过的密文
	 * @param cipherTransformation 算法
	 * @param privateKey BASE64 编码过的私钥
	 * @return 原文
	 * @throws CryptException
	 */
	public static final String RSAWithBase64(String content, String cipherTransformation, String privateKey) throws CryptException {
		return new String(RSA(Base64.decodeBase64(content), cipherTransformation, privateKey));
	}
	
	/**
	 * RSA 私钥解密
	 * 
	 * @param content 密文
	 * @param cipherTransformation 算法
	 * @param privateKey 私钥
	 * @return
	 * @throws CryptException
	 */
	public static final byte[] RSA(byte[] content, String cipherTransformation, String privateKey) throws CryptException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(Algorithms.RSA);
			PrivateKey key = keyFactory.generatePrivate(keySpec);
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(content);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			logger.info("RSA 解密失败！密文 - {}， 算法 - {}， 密钥  - {}！", Arrays.toString(content), cipherTransformation, privateKey);
			throw new CryptException("RSA 解密失败", e);
		} 
	}
	
	/**
	 * AES 解密
	 * 
	 * @param data BASE64 编码的密文
	 * @param cipherTransformation
	 * @param key
	 * @return
	 * @throws CryptException 
	 */
	public static final String AESWithBase64(String content, String cipherTransformation, String key) throws CryptException {
		return AES(Base64.decodeBase64(content), cipherTransformation, key);
	}
	
	/**
	 * AES 解密
	 * 
	 * @param content
	 * @param cipherTransformation
	 * @param key
	 * @return
	 * @throws CryptException
	 */
	public static final String AES(byte[] content, String cipherTransformation, String key) throws CryptException {
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(Consts.UTF_8), Algorithms.AES);
		byte[] encodedKey = secretKey.getEncoded();
		SecretKeySpec seckey = new SecretKeySpec(encodedKey, Algorithms.AES);
		try {
			Cipher cipher = Cipher.getInstance(Algorithms.AES_ECB_PKCS5);
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			byte[] valueByte = cipher.doFinal(content);
			return new String(valueByte, Consts.UTF_8);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			logger.info("AES 解密失败！密文 - {}， 算法 - {}， 密钥  - {}！", content, cipherTransformation, key);
			throw new CryptException("AES 解密失败", e);
		}
	}
}
