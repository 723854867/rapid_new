package org.rapid.util.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.util.Properties;

import org.rapid.util.common.Consts;
import org.rapid.util.common.model.enums.Protocol;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.reflect.ClassUtil;

public class ResourceUtil {
	
	private static final String CLASSPATH_URL_PREFIX = "classpath:";
	
	private static final String URL_PROTOCOL_FILE = "file";

	public static URL getURL(String resourceLocation) throws FileNotFoundException {
		if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
			String path = resourceLocation.substring(CLASSPATH_URL_PREFIX.length());
			ClassLoader cl = ClassUtil.getDefaultClassLoader();
			URL url = (cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path));
			if (url == null) {
				String description = "class path resource [" + path + "]";
				throw new FileNotFoundException(description +
						" cannot be resolved to URL because it does not exist");
			}
			return url;
		}
		try {
			return new URL(resourceLocation);
		} catch (MalformedURLException ex) {
			try {
				return new File(resourceLocation).toURI().toURL();
			}catch (MalformedURLException ex2) {
				throw new FileNotFoundException("Resource location [" + resourceLocation + "] is neither a URL not a well-formed file path");
			}
		}
	}
	
	public static File getFile(String resourceLocation) throws NoSuchFileException {
		if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
			String path = resourceLocation.substring(CLASSPATH_URL_PREFIX.length());
			String description = "class path resource [" + path + "]";
			ClassLoader cl = ClassUtil.getDefaultClassLoader();
			URL url = (cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path));
			if (url == null) 
				throw new NoSuchFileException(description + " cannot be resolved to absolute file path because it does not exist");
			return getFile(url, description);
		}
		try {
			return getFile(new URL(resourceLocation));
		} catch (MalformedURLException ex) {
			return new File(resourceLocation);
		}
	}
	
	public static File getFile(URL resourceUrl) throws NoSuchFileException {
		return getFile(resourceUrl, "URL");
	}
	
	public static File getFile(URL resourceUrl, String description) throws NoSuchFileException {
		if (!URL_PROTOCOL_FILE.equals(resourceUrl.getProtocol())) 
			throw new NoSuchFileException(description + " cannot be resolved to absolute file path " + "because it does not reside in the file system: " + resourceUrl);
		try {
			return new File(toURI(resourceUrl).getSchemeSpecificPart());
		} catch (URISyntaxException ex) {
			return new File(resourceUrl.getFile());
		}
	}
	
	public static URI toURI(URL url) throws URISyntaxException {
		return toURI(url.toString());
	}
	
	public static URI toURI(String location) throws URISyntaxException {
		return new URI(StringUtil.replace(location, " ", "%20"));
	}
	
	public static InputStream getResourceAsStream(Class<?> clazz, String entry) throws MalformedURLException, IOException { 
        String jarPath = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
        return new URL(Protocol.JAR.value() + jarPath + "!" + entry).openStream();
	}
	
	public static final byte[] readBuffer(Class<?> clazz, String name) throws IOException { 
		BufferedInputStream in = null;
		byte[] buffer = null;
		try {
			in = new BufferedInputStream(clazz.getResourceAsStream(name));
			buffer = new byte[in.available()];
			in.read(buffer);
		} finally {
			if (null != in)
				in.close();
		}
		return buffer;
	}
	
	public static Properties loadProperties(Class<?> clazz, String name) throws IOException {
		InputStream in = clazz.getResourceAsStream(name);
		if (null == in)
			throw new FileNotFoundException(name);
		try {
			Properties properties = new Properties();
			properties.load(new InputStreamReader(in, Consts.UTF_8));
			return properties;
		} finally {
			in.close();
		}
	}
	
	public static Properties loadProperties(String name) throws NoSuchFileException, IOException {
		File file = getFile(name);
		InputStream in = null;
		try {
			Properties properties = new Properties();
			in = new FileInputStream(file);
			properties.load(in);
			return properties;
		} finally {
			if (null != in)
				in.close();
		}
	}
}
