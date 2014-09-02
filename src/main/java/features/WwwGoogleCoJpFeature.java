package features;

import operators.wwwgooglecojp.WwwGoogleCoJpOperator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;
import support.PageProperties;

public class WwwGoogleCoJpFeature {
	public void login() {
		WebDriver driver = new FirefoxDriver();
		WwwGoogleCoJpOperator ope = new WwwGoogleCoJpOperator(new WwwGoogleCoJpPage(driver));

		String email = PageProperties.getInstance().getProperty("wwwgooglecojp.loginId");
		String pass = PageProperties.getInstance().getProperty("wwwgooglecojp.loginPass");
		ope.enterLogin(email, pass);

		ope.quit();
	}
}
