package features;

import operators.wwwgooglecojp.WwwGoogleCoJpOperator;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;

public class TestWwwGoogleCoJp {
	public static void main(String[] args) {
		WwwGoogleCoJpPage page =  new WwwGoogleCoJpPage(new FirefoxDriver());
		WwwGoogleCoJpOperator ope = new WwwGoogleCoJpOperator(page);
		ope.clickGmail();
		page.quit();
	}
}