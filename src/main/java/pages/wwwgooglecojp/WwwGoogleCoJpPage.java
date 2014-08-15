package pages.wwwgooglecojp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.supers.AbstractSuperPage;

public class WwwGoogleCoJpPage extends AbstractSuperPage {
	public WwwGoogleCoJpPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected String initializeUrl() {
		return "https://www.google.co.jp/";
	}

	public WebElement spanLogin() {
		return findId("gbgs4");
	}

	public WebElement spanGmail() {
		return findId("gb_23");
	}
}
