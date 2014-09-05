package features;

import operators.wwwgooglecojp.WwwGoogleCoJpOperator;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import support.GenericUtils;

public class WwwGoogleCoJpFeatureTest {
	@Test
	public void testLogin() throws Exception {
		for (WebDriver driver : GenericUtils.getWebDriverList()) {
			WwwGoogleCoJpOperator ope = WwwGoogleCoJpFeature.login(driver);
			ope.quit();
		}
	}
}