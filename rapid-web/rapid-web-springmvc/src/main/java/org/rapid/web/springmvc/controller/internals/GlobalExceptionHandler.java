package org.rapid.web.springmvc.controller.internals;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.rapid.util.common.Consts;
import org.rapid.util.common.model.code.Code;
import org.rapid.util.common.model.message.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<Void> handler(Exception e) {
		Result<Void> result = new Result<Void>(Code.SYSTEM_ERROR);
		if (e instanceof IllegalArgumentException) 
			logger.warn("Controller 方法参数绑定失败，请注意是否使用 @RequestParam！", e);
		else
			logger.warn("系统错误！", e);
		return result;
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public Result<Void> httpRequestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException ex, HttpServletResponse response) {
		Set<HttpMethod> methods = ex.getSupportedHttpMethods();
		StringBuilder builder = new StringBuilder();
		for (HttpMethod method : methods) 
			builder.append(method.name()).append(",");
		builder.deleteCharAt(builder.length() - 1);
		logger.warn("不支持的请求方法 {}，支持 {}！", ex.getMethod(), ex.getSupportedHttpMethods());
		return Consts.Results.UNSUPPORTED_HTTP_METHOD;
	}
	
	@ExceptionHandler( {HttpMessageNotReadableException.class, MissingServletRequestParameterException.class} )
	@ResponseBody
	public Result<Void> httpMessageNotReadableException(Exception ex) {
		logger.warn("客户端请求参数错误：{}", ex.toString());
		return Consts.Results.PARAM_ERROR;
	}
	
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public Result<Void> bindExceptionHandler(BindException ex) {
		return _validatorError(ex.getBindingResult());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public Result<Void> constraintViolationExceptionHandler(ConstraintViolationException ex) { 
		Result<Void> result = new Result<Void>(Code.PARAM_ERROR);
		Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
		StringBuilder reason = new StringBuilder("[");
		for (ConstraintViolation<?> constraintViolation : set) 
			reason.append(((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName()).append("-").append(constraintViolation.getMessage()).append(";");
		reason.deleteCharAt(reason.length() - 1);
		reason.append("]");
		result.setDesc(result.getDesc() + ":" + reason.toString());
		return result;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		return _validatorError(ex.getBindingResult());
	}
	
	/**
	 * 不支持的 HTTP contentType
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Result<String> unsupportedMediaTypeHandler(HttpMediaTypeNotSupportedException ex) { 
		return new Result<String>(Code.UNSUPPORTED_HTTP_CONTENT_TYPE, ex.getContentType().toString());
	}
	
	/**
	 * 上传文件超过上限
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public Result<Long> uploadSizeExceededHandler(MaxUploadSizeExceededException ex) {
		return new Result<Long>(Code.UPLOAD_SIZE_EXCEEDED, ex.getMaxUploadSize());
	}
	
	private Result<Void> _validatorError(BindingResult bindingResult) { 
		List<FieldError> errors = bindingResult.getFieldErrors();
		StringBuilder reason = new StringBuilder("[");
		for (FieldError error : errors) 
			reason.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
		reason.deleteCharAt(reason.length() - 1);
		reason.append("]");
		Result<Void> result = new Result<Void>(Code.PARAM_ERROR);
		result.setDesc(result.getDesc() + ":" + reason.toString());
		return result;
	}
}
