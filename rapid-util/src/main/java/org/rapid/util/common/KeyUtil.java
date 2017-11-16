package org.rapid.util.common;

import java.util.Random;

import org.rapid.util.lang.TimeUtil;

public class KeyUtil {
	
	/**
	 * 流水号
	 * 
	 * @return
	 */
	public static final String warterNo() {
		String date = TimeUtil.getDate(TimeUtil.yyyyMMddHHmmss);
		int random = (int) ((Math.random() * 9 + 1) * 10000);
		return date + random;
	}

	/**
	 * 生成指定位数的数字数字字符串
	 * 
	 * @param bitNum
	 * @return
	 */
	public static String code(int bitNum) { 
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		while (sb.length() < bitNum) 
			sb.append(random.nextInt(10));
		return sb.toString();
	}
}
