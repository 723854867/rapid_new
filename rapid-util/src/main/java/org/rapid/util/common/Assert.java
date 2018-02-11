package org.rapid.util.common;

import javax.exception.AssertFailException;

public class Assert {
	
	public static <T> T isNull(T argument, String name) {
		if (argument != null)
            throw new AssertFailException(name + " may not null");
        return argument;
	}
	
	public static <T> T notNull(T argument, String name) {
        if (argument == null)
            throw new AssertFailException(name + " may not be null");
        return argument;
    }
	
	public static void notNull(String message, Object...args) {
		for (Object object : args) {
			if (null == object)
	            throw new AssertFailException(message);
		}
    }
	
	public static int notNegative(int n, String name) {
        if (n < 0) 
            throw new AssertFailException(name + " may not be negative");
        return n;
    }
}
