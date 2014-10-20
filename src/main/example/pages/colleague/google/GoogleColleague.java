package pages.colleague.google;

import org.openqa.selenium.WebElement;
import pages.colleague.Colleague;
import pages.mediator.Mediator;

public class GoogleColleague extends Colleague {
	public GoogleColleague(Mediator mediator) {
		super(mediator);
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

	public WebElement inputLoginBtn() {
		return findXpath("//input[@id='signIn']");
	}
}
