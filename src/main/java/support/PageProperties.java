package support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PageProperties {
	private static PageProperties instance = new PageProperties();
	private Properties prop = new Properties();

	private PageProperties() {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream("page.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PageProperties getInstance() {
		return instance;
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}
