package org.rapid.util.common.model.message;

import java.io.Serializable;

import org.rapid.util.common.model.code.ICode;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = 9152613615847846961L;
	
	private T attach;
	private int code;
	private String desc;
	
	public Result() {}
	
	public Result(ICode code) {
		this.code = code.getId();
		this.desc = code.desc();
	}
	
	public Result(ICode code, T attach) {
		this.code = code.getId();
		this.desc = code.desc();
		this.attach = attach;
	}
	
	public T getAttach() {
		return attach;
	}
	
	public void setAttach(T attach) {
		this.attach = attach;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
}