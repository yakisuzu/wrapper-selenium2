package support.properties;

public class SystemProperties extends AProperties {
	static {
		new SystemProperties("system.properties");
	}

	private SystemProperties(String file) {
		super(file);
	}

	public static SystemProperties getInstance() {
		return getInstance(SystemProperties.class);
	}
}
