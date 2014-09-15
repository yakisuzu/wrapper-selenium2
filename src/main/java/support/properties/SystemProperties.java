package support.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties extends AProperties {
	private static Logger LOG = LoggerFactory.getLogger(SystemProperties.class);
	private static SystemProperties instance = new SystemProperties();
	private Properties prop = new Properties();

	private SystemProperties() {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream("system.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			LOG.error("properties取得エラー", e);
		}
	}

	public static SystemProperties getInstance() {
		return instance;
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}
