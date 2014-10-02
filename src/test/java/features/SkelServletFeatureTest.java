package features;

import operators.SkelServlet.SkelServletOperator;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import support.GenericUtils;
import support.ProcessBuilderUtils;

public class SkelServletFeatureTest {
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ProcessBuilderUtils.killProcessIe();
	}

	@Test
	public void testLogin() throws Exception {
		for (WebDriver driver : GenericUtils.getWebDriverList()) {
			SkelServletFeature.winOpen(driver);
			driver.quit();
		}
	}
}