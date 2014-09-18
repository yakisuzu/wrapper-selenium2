package support.properties;

public class PageProperties extends AProperties {
	static {
		new PageProperties("page.properties");
	}

	private PageProperties(String file) {
		super(file);
	}

	public static PageProperties getInstance() {
		return getInstance(PageProperties.class);
	}
}
