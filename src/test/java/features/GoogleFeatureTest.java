package features;

import operators.google.GoogleOperator;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import support.GenericUtils;

public class GoogleFeatureTest {
	@Test
	public void testLogin() throws Exception {
		for (WebDriver driver : GenericUtils.getWebDriverList()) {
			GoogleOperator ope = null;
			try {
				ope = GoogleFeature.login(driver);
			} finally {
				if (ope != null) {
					ope.quit();
				}
			}
		}
	}
}