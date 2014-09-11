package pages.colleague.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.mediator.Mediator;
import support.PageProperties;

public class GoogleColleague extends Mediator {
	public GoogleColleague(Mediator page){
		super(page);
	}

	public GoogleColleague(WebDriver driver) {
		super(driver);
	}

	@Override
	protected String initializeUrl() {
		return PageProperties.getInstance().getProperty("wwwgooglecojp.url");
	}

	public WebElement aLogin() {
		return findXpath("//a[contains(@href,'https://mail.google.com/mail/')]");
	}

	public WebElement aGmail() {
		return findXpath("//a[contains(@href,'https://accounts.google.com/ServiceLogin')]");
	}

	public WebElement inputLoginEmail() {
		return findXpath("//input[@id='Email']");
	}

	public WebElement inputLoginPasswd() {
		return findXpath("//input[@id='Passwd']");
	}

	public WebElement inputLoginBtn(){
		return findXpath("//input[@id='signIn']");
	}
}
