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
		return getPageProperties().getProperty("wwwgooglecojp.url");
	}

	public WebElement spanLogin() {
		return findXpath("//span(@id='gbgs4')");
	}

	public WebElement spanGmail() {
		return findXpath("//span(@id='gb_23')");
	}
}
