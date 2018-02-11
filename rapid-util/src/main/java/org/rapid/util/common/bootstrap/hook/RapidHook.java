package org.rapid.util.common.bootstrap.hook;

import java.io.IOException;
import java.util.Properties;

import org.rapid.util.common.bootstrap.Rapid;
import org.rapid.util.common.model.enums.FileType;
import org.rapid.util.common.model.enums.Locale;
import org.rapid.util.io.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RapidHook implements InitialHook {
	
	private static final Logger logger = LoggerFactory.getLogger(RapidHook.class);
	
	@Autowired
	private Rapid rapid;

	@Override
	public void execute() {
		_initLanguage();
	}
	
	/**
	 * 初始化系统自定义语言
	 * 
	 * @param rapid
	 */
	private void _initLanguage() { 
		Locale locale = rapid.locale();
		String languageFile = "/conf/lang/lang_" + locale.mark() + FileType.PROPERTIES.suffix();
		try {
			Properties properties = ResourceUtil.loadProperties(RapidHook.class, languageFile);
			rapid.language().load(properties);
			properties.clear();
		} catch (IOException e) {
			logger.error("System language file load failure, system will closed!", e);
		}
	}
}
