package features;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import support.Generic;

public class WwwGoogleCoJpFeatureTest {
	@Test
	public void testLogin() throws Exception {
		for (WebDriver driver : Generic.getWebDriverList()) {
			WwwGoogleCoJpFeature.login(driver);
		}
	}
}