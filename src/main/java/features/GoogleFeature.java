package features;

import operators.google.GoogleOperator;
import org.openqa.selenium.WebDriver;
import support.PageProperties;

public class GoogleFeature {
	public static GoogleOperator login(WebDriver driver) {
		GoogleOperator ope = new GoogleOperator(driver);

		String email = PageProperties.getInstance().getProperty("wwwgooglecojp.loginId");
		String pass = PageProperties.getInstance().getProperty("wwwgooglecojp.loginPass");
		ope.enterLogin(email, pass);
		return ope;
	}
}
