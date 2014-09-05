package features;

import operators.wwwgooglecojp.WwwGoogleCoJpOperator;
import org.openqa.selenium.WebDriver;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;
import support.PageProperties;

public class WwwGoogleCoJpFeature {
	public static WwwGoogleCoJpOperator login(WebDriver driver) {
		WwwGoogleCoJpOperator ope = new WwwGoogleCoJpOperator(new WwwGoogleCoJpPage(driver));

		String email = PageProperties.getInstance().getProperty("wwwgooglecojp.loginId");
		String pass = PageProperties.getInstance().getProperty("wwwgooglecojp.loginPass");
		ope.enterLogin(email, pass);
		return ope;
	}
}
