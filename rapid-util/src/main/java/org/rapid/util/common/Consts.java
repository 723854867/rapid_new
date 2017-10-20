package org.rapid.util.common;

import java.nio.charset.Charset;

import org.rapid.util.common.model.code.Code;
import org.rapid.util.common.model.message.Result;

public interface Consts {

	final Charset UTF_8				= Charset.forName("UTF-8");
	
	interface Results {
		Result<Void> PARAM_ERROR					= new Result<Void>(Code.PARAM_ERROR);
		Result<Void> UNSUPPORTED_HTTP_METHOD		= new Result<Void>(Code.UNSUPPORTED_HTTP_METHOD);
	}
}
