package org.rapid.util.net;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.exception.HTTPException;

import org.rapid.util.common.serializer.Serializer;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttp {

	private OkHttpClient client;
	
	public void init() {
		this.client = new OkHttpClient();
	}
	
	/**
	 * HTTP get 请求
	 * 
	 * @param url 请求的 url
	 * @param serializer 序列化类
	 * @param clazz 响应类型
	 * @return
	 * @throws IOException
	 */
	public <T> T get(String url, Serializer serializer, Class<T> clazz) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) 
			throw new HTTPException(response.code());
		byte[] body = response.body().bytes();
		return serializer.deserial(body, clazz);
	}
	
	/**
	 * 异步的 get 请求
	 * 
	 * @param url
	 * @param callback
	 */
	public void getAsync(String url, Callback callback) { 
		client.newCall(new Request.Builder().url(url).build()).enqueue(callback);
	}
	
	/**
	 * post 提交请求
	 * 
	 * @param url
	 * @param content
	 * @param contentType 请求的文档类型
	 * @param serializer
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public <T> T post(String url, String content, ContentType contentType, Serializer serializer, Class<T> clazz) throws IOException {
		RequestBody body = RequestBody.create(MediaType.parse(contentType.mark()), content);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) 
			throw new HTTPException(response.code());
		byte[] resBody = response.body().bytes();
		return serializer.deserial(resBody, clazz);
	}
	
	/**
	 * post 表单提交键值对
	 * 
	 * @param url
	 * @param params
	 * @param serializer
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public <T> T postForm(String url, Map<String, Object> params, Serializer serializer, Class<T> clazz) throws IOException { 
		FormBody.Builder builder = new FormBody.Builder();
		for (Entry<String, Object> entry : params.entrySet())
			builder.add(entry.getKey(), entry.getValue().toString());
		RequestBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) 
			throw new HTTPException(response.code());
		byte[] resBody = response.body().bytes();
		return serializer.deserial(resBody, clazz);
	}
	
	/**
	 * post 异步请求
	 * 
	 * @param url
	 * @param content
	 * @param contentType
	 * @param callback
	 */
	public void postAsync(String url, String content, ContentType contentType, Callback callback) {
		RequestBody body = RequestBody.create(MediaType.parse(contentType.mark()), content);
		Request request = new Request.Builder().url(url).post(body).build();
		client.newCall(request).enqueue(callback);
	}
	
	/**
	 * 异步表单
	 * 
	 * @param url
	 * @param params
	 * @param callback
	 */
	public void postFormAsyn(String url, Map<String, Object> params, Callback callback) { 
		FormBody.Builder builder = new FormBody.Builder();
		for (Entry<String, Object> entry : params.entrySet())
			builder.add(entry.getKey(), entry.getValue().toString());
		RequestBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		client.newCall(request).enqueue(callback);;
	}
}
