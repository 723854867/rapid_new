package org.rapid.util.common.bootstrap;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Properties;
import java.util.ServiceLoader;

import javax.annotation.PostConstruct;

import org.rapid.util.common.Language;
import org.rapid.util.common.SpringContextUtil;
import org.rapid.util.common.bootstrap.hook.InitialHook;
import org.rapid.util.common.model.enums.Env;
import org.rapid.util.common.model.enums.Locale;
import org.rapid.util.io.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("springContextUtil")
public class Rapid {
	
	private static final Logger logger = LoggerFactory.getLogger(Rapid.class);
	
	private static final String CONFIG_ENV			= "rapid.env";
	private static final String CONFIG_LOCALE		= "rapid.locale";
	
	private Env env;								// 运行环境
	private Locale locale;							// 语言环境
	private Language language;						// 语言类
	
	@PostConstruct
	private void _init() throws IOException {
		_configurationParser();
		
		logger.info("Initializing application hooks...");
		this.language = new Language();
		ServiceLoader<InitialHook> hooks = ServiceLoader.load(InitialHook.class);
		int count = 0;
		for (InitialHook hook : hooks) {
			SpringContextUtil.autowireBean(hook);
			hook.execute();
			count++;
		}
		logger.info("Total {} application hooks initialized!", count);
	}
	
	private void _configurationParser() throws IOException {
		try {
			Properties properties = ResourceUtil.loadProperties("classpath:conf/rapid.properties");
			this.env = Env.match(properties.getProperty(CONFIG_ENV));
			this.locale = Locale.match(properties.getProperty(CONFIG_LOCALE));
		} catch (NoSuchFileException e) {
			logger.info("No rapid.properties from the classpath is specified, the default system configuration is used");
		}
		
		this.env = null != this.env ? this.env : Env.LOCAL;
		this.locale = null != this.locale ? this.locale : Locale.EN_US;
	}
	
	public final Env env() {
		return this.env;
	}
	
	public final Locale locale() {
		return this.locale;
	}
	
	public final Language language() {
		return this.language;
	}
}
