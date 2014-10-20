package features;

import operators.google.GoogleOperator;
import org.openqa.selenium.WebDriver;
import support.properties.PageProperties;

public class GoogleFeature {
	public static GoogleOperator login(WebDriver driver) {
		GoogleOperator ope = new GoogleOperator(driver);

		String email = PageProperties.getInstance().getString("wwwgooglecojp.loginId");
		String pass = PageProperties.getInstance().getString("wwwgooglecojp.loginPass");
		ope.enterLogin(email, pass);
		return ope;
	}
}
