package org.rapid.util.net;

/**
 * 文档类型
 * 
 * @author admin
 */
public enum ContentType {

	APPLICATION_JSON_UTF_8("application/json;charset=utf-8");
	
	private String mark;
	
	private ContentType(String mark) {
		this.mark = mark;
	}
	
	public String mark() {
		return mark;
	}
}
