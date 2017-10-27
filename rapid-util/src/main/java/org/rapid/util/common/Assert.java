package org.rapid.util.common;

public class Assert {
	
	public static <T> T notNull(T argument, String name) {
        if (argument == null)
            throw new IllegalArgumentException(name + " may not be null");
        return argument;
    }

	public static int notNegative(int n, String name) {
        if (n < 0) 
            throw new IllegalArgumentException(name + " may not be negative");
        return n;
    }
}
