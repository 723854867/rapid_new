package org.rapid.util.lang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
	
	public static final String ISO8601_UTC 				= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String ISO8601_UTC_S 			= "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String yyyyMMddHHmmss			= "yyyyMMddHHmmss";
	public static final String YYYY_MM_DD_HH_MM_SS		= "yyyy-MM-dd HH:mm:ss";

	public static final TimeZone TIMEZONE_UTC			= TimeZone.getTimeZone("UTC");
	public static final TimeZone TIMEZONE_GMT_8			= TimeZone.getTimeZone("GMT+8:00");

	public static int currentTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}
	
	/**
	 * iso8601 格林威治时间
	 * 
	 * @return
	 */
	public static final String iso8601UTCDate() { 
		return getDate(ISO8601_UTC, System.currentTimeMillis(), TIMEZONE_UTC);
	}
	
	/**
	 * 由于iso8601只精确到秒，因此这里做了一个变种，该方法的格式类似：2017-10-12T10:01:46.818Z
	 * 
	 * @return
	 */
	public static final String iso8601UTCMillisDate() {
		return getDate(ISO8601_UTC_S, System.currentTimeMillis(), TIMEZONE_UTC);
	}
	
	public static final String getDate(String format) {
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TIMEZONE_GMT_8);
		return df.format(new Date(System.currentTimeMillis()));
	}
	
	public static final String getDate(String format, long timestamp) {
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TIMEZONE_GMT_8);
		return df.format(new Date(timestamp));
	}
	
	public static final String getDate(String format, long timestamp, TimeZone timeZone) {
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(timeZone);
		return df.format(new Date(timestamp));
	}
	
	public static final long iso8601Timestamp(String date) {
		return getTime(date, ISO8601_UTC_S, TIMEZONE_UTC);
	}
	
	public static final long getTime(String date, String format, TimeZone zone) { 
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(zone);
		try {
			return df.parse(date).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
}
