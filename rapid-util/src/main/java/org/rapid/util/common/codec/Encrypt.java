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

/**
 * 加密工具类
 * 
 * 1、字符串格式的密钥在未在特殊说明情况下都为 BASE64 编码格式<br/>
 * 2、由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 3、非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * 
 * @author lynn
 */
public class Encrypt {

	private static final Logger logger = LoggerFactory.getLogger(Encrypt.class);
	
	/**
	 * RSA 签名：
	 * 
	 * @param content 代签名数据
	 * @param signAlgorithm 签名算法
	 * @param privateKey 私钥
	 * @return BASE64 编码过的签名
	 * @throws CryptException
	 */
	public static final String RSASignWithBase64(String content, String signAlgorithm, String privateKey) throws CryptException {
		return new String(Base64.encodeBase64(RSASign(content, signAlgorithm, privateKey)));
	}
	
	/**
	 * RSA 签名：
	 * 
	 * @param content 待签数据
	 * @param signAlgorithm 签名算法
	 * @param privateKey 私钥
	 * @return 签名
	 * @throws CryptException 
	 */
	public static final byte[] RSASign(String content, String signAlgorithm, String privateKey) throws CryptException {
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(Algorithms.RSA);
			PrivateKey key = keyFactory.generatePrivate(priPKCS8);
			Signature signature = Signature.getInstance(signAlgorithm);
			signature.initSign(key);
			signature.update(content.getBytes(Consts.UTF_8));
			return signature.sign();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
			logger.info("RSA 签名失败，待签数据- {}， 算法 - {}， 密钥  - {}！", content, signAlgorithm, privateKey);
			throw new CryptException("RSA 签名失败", e);
		}
	}
	
	/**
	 * RSA 加密
	 * 
	 * @param content 待加密内容
	 * @param cipherTransformation 加密算法
	 * @param publicKey BASE64 编码过的公钥
	 * @return 编码过得密文
	 * @throws CryptException
	 */
	public static final String RSAWithBase64(String content, String cipherTransformation, String publicKey) throws CryptException {
		return Base64.encodeBase64String(RSA(content, cipherTransformation, publicKey));
	}
	
	/**
	 * RSA 加密
	 * 
	 * @param content 待加密内容
	 * @param cipherTransformation 加密算法
	 * @param publicKey 加密公钥
	 * @return
	 * @throws CryptException
	 */
	public static final byte[] RSA(String content, String cipherTransformation, String publicKey) throws CryptException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(Algorithms.RSA);
			PublicKey key = keyFactory.generatePublic(keySpec);
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(content.getBytes(Consts.UTF_8));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			logger.info("RSA 加密失败！待加密内容 - {}， 算法 - {}， 公钥  - {}！", content, cipherTransformation, publicKey);
			throw new CryptException("RSA 加密失败", e);
		}
	}
	
	/**
	 * aes 加密，密文用 Base64 编码返回
	 * 
	 * @param content 待加密内容
	 * @param cipherTransformation 加密算法
	 * @param key 密钥
	 * @return
	 * @throws CryptException
	 */
	public static final String AESwithBase64(String content, String cipherTransformation, String key) throws CryptException {
		byte[] data = AES(content, cipherTransformation, key);
		return Base64.encodeBase64String(data);
	}
	
	/**
	 * AES 加密
	 * 
	 * @param content
	 * @param cipherTransformation
	 * @param key
	 * @return
	 * @throws CryptException
	 */
	public static final byte[] AES(String content, String cipherTransformation, String key) throws CryptException {
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(Consts.UTF_8), Algorithms.AES); 
		byte[] encoded = secretKey.getEncoded();
		SecretKeySpec seckey = new SecretKeySpec(encoded, Algorithms.AES);
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(Cipher.ENCRYPT_MODE, seckey);// 初始化
			return cipher.doFinal(content.getBytes(Consts.UTF_8));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			logger.info("AES 加密失败！待加密内容 - {}， 算法 - {}， 密钥  - {}！", content, cipherTransformation, key);
			throw new CryptException("AES 加密失败", e);
		}
	}
}
