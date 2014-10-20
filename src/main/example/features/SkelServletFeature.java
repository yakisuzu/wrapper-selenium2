package features;

import operators.SkelServlet.SkelServletOperator;
import org.openqa.selenium.WebDriver;

public class SkelServletFeature {
	public static SkelServletOperator winOpen(WebDriver driver) {
		SkelServletOperator ope = new SkelServletOperator(driver);

		ope.clickWinOpen();
		return ope;
	}
}
