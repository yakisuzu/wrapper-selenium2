package features;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import support.GenericUtils;
import support.ProcessBuilderUtils;

public class GoogleFeatureTest {
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ProcessBuilderUtils.killProcessIe();
	}

	@Test
	public void testLogin() throws Exception {
		for (WebDriver driver : GenericUtils.getWebDriverList()) {
			GoogleFeature.login(driver);
			driver.quit();
		}
	}
}