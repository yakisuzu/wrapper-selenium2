package features;

import operators.google.GoogleOperator;
import org.openqa.selenium.WebDriver;
import pages.mediator.google.GoogleMediator;
import support.PageProperties;

public class GoogleFeature {
	public static GoogleOperator login(WebDriver driver) {
		GoogleOperator ope = new GoogleOperator(new GoogleMediator(driver));

		String email = PageProperties.getInstance().getProperty("wwwgooglecojp.loginId");
		String pass = PageProperties.getInstance().getProperty("wwwgooglecojp.loginPass");
		ope.enterLogin(email, pass);
		return ope;
	}
}
