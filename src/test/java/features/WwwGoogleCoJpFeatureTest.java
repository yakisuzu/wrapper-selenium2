package features;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import support.GenericUtils;

public class WwwGoogleCoJpFeatureTest {
	@Test
	public void testLogin() throws Exception {
		for (WebDriver driver : GenericUtils.getWebDriverList()) {
			WwwGoogleCoJpFeature.login(driver);
		}
	}
}