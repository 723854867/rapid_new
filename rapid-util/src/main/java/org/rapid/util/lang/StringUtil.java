package org.rapid.util.lang;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class StringUtil {

	private static Random UUID;
	
	static {
		SecureRandom secureRandom = new SecureRandom();
		byte[] seed = new byte[8];
		secureRandom.nextBytes(seed);
		UUID = new Random(new BigInteger(seed).longValue());
	}
	
	public static final String uuid() { 
		byte[] randomBytes = new byte[16];
		UUID.nextBytes(randomBytes);
		long mostSigBits = 0;
		for (int i = 0; i < 8; i++) 
			mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
		long leastSigBits = 0;
		for (int i = 8; i < 16; i++)
			leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
		return new UUID(mostSigBits, leastSigBits).toString();
	}
	
	public static final boolean hasText(CharSequence str) {
		if (!hasLength(str))
			return false;
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i)))
				return true;
		}
		return false;
	}
	
	public static final  boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * 替换字符串
	 * 
	 * @param string
	 * @param oldPattern
	 * @param newPattern
	 * @return
	 */
	public static String replace(String string, String oldPattern, String newPattern) {
		if (!hasLength(string) || !hasLength(oldPattern) || newPattern == null)
			return string;
		StringBuilder sb = new StringBuilder();
		int pos = 0;
		int index = string.indexOf(oldPattern);
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(string.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = string.indexOf(oldPattern, pos);
		}
		sb.append(string.substring(pos));
		return sb.toString();
	}
}
