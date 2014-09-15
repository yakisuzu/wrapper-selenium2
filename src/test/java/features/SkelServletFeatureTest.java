package features;

import operators.SkelServlet.SkelServletOperator;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import support.GenericUtils;

public class SkelServletFeatureTest {
	@Test
	public void testLogin() throws Exception {
		for (WebDriver driver : GenericUtils.getWebDriverList()) {
			SkelServletOperator ope = SkelServletFeature.winOpen(driver);
			ope.quit();
		}
	}
}