package org.rapid.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp extends TestCase {

	private OkHttpClient client;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.client = new OkHttpClient();
	}
	
	/**
	 * get 请求
	 * 
	 * @throws IOException
	 */
	public void testGet() throws IOException {
		Request request = new Request.Builder().url("https://www.baidu.com").build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
			System.out.println(response.body().string());
		else
			throw new IOException("Unexpected code " + response);
	}
	
	/**
	 * post json
	 * 
	 * @throws IOException
	 */
	public void testPostJson() throws IOException {
		String json = null;
		RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
		Request request = new Request.Builder().url("").post(body).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
			System.out.println(response.body().string());
		else
			throw new IOException("Unexpected code " + response);
	}
	
	/**
	 * post 表单请求
	 * 
	 * @throws IOException
	 */
	public void testPostUrlEncode() throws IOException {
		RequestBody body = new FormBody.Builder().add("mobile", "13105716369").build();
		Request request = new Request.Builder()
				.url("http://39.108.230.148:9083/mobile/user/registerSendSms")
				.post(body)
				.build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
			System.out.println(response.body().string());
		else
			throw new IOException("Unexpected code " + response);
	}
	
	/**
	 * 对于小文档来说 string() 方法很方便也很高效，但是如果是超过1MB的文件，应避免使用 string() 方法，因为他会把整个文档加载到内存中。应该使用流的方式来处理body
	 * @throws Exception
	 */
	public void testFileLoad() throws Exception {
		Request request = new Request.Builder().url("http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html").build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);
		Headers headers = response.headers();
		for (int i = 0; i < headers.size(); i++) 
			System.out.println(headers.name(i) + ":" + headers.value(i));
		System.out.println(new String(response.body().bytes(), "gb2312"));
	}
	
	/**
	 * 典型的HTTP头 像是一个 Map<String, String> :每个字段都有一个或没有值。但是一些头允许多个值，像Guava的Multimap。例如：HTTP响应里面提供的Vary响应头，就是多值的。OkHttp的api试图让这些情况都适用。
当写请求头的时候，使用header(name, value)可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用addHeader(name, value)可以添加多值（添加，不移除已有的）。
当读取响应头时，使用header(name)返回最后出现的name、value。通常情况这也是唯一的name、value。如果没有值，那么header(name)将返回null。如果想读取字段对应的所有值，使用headers(name)会返回一个list。
为了获取所有的Header，Headers类支持按index访问。
	 * @throws InterruptedException
	 */
	public void testAsync() throws InterruptedException {
		Request request = new Request.Builder().url("http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html").build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call arg0, Response response) throws IOException {
				if (!response.isSuccessful())
					throw new IOException("Unexpected code " + response);
				System.out.println(Thread.currentThread());
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Headers headers = response.headers();
				for (int i = 0; i < headers.size(); i++) 
					System.out.println(headers.name(i) + ":" + headers.value(i));
				System.out.println(response.body().string());
			}
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				arg1.printStackTrace();
			}
		});
		System.out.println(Thread.currentThread());
		TimeUnit.SECONDS.sleep(60);
	}
}
