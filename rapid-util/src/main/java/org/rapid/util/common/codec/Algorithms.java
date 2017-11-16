package org.rapid.util.common.codec;

public interface Algorithms {

	final String RSA			 						= "RSA";
	final String AES									= "AES";
	
	final String RSA_ECB_PKCS1							= "RSA/ECB/PKCS1Padding";
	final String AES_ECB_PKCS5							= "AES/ECB/PKCS5Padding";

	final String SIGN_MD5WITHRSA						= "MD5withRSA";
	final String SIGN_SHA1WITHRSA						= "SHA1WithRSA";
}
