package support.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class AProperties {
	private static Logger LOG = LoggerFactory.getLogger(AProperties.class);
	private static ClassLoader classLoader = AProperties.class.getClassLoader();
	private static Map<Class<? extends AProperties>, AProperties> propertiesMap = new HashMap<Class<? extends AProperties>, AProperties>();
	private Properties prop;

	private AProperties() {
	}

	protected AProperties(String file) {
		this();
		InputStream is = classLoader.getResourceAsStream(file);

		prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			LOG.error("properties取得エラー", e);
		}

		propertiesMap.put(this.getClass(), this);
	}

	@SuppressWarnings("unchecked")
	protected static <P extends AProperties> P getInstance(Class<P> clazz) {
		return (P) propertiesMap.get(clazz);
	}

	public String getString(String key) {
		return prop.getProperty(key);
	}

	public int getInt(String key) {
		return Integer.valueOf(prop.getProperty(key));
	}
}
