package org.rapid.util.common.model.code;

import org.rapid.util.common.model.Unique;

/**
 * 错误码
 * 
 * @author lynn
 */
public interface ICode extends Unique<Integer> {
	
	String desc();
}