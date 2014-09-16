package pages.colleague.google;

import org.openqa.selenium.WebElement;
import pages.colleague.Colleague;
import pages.mediator.google.GoogleMediator;

public class GoogleColleague extends Colleague<GoogleMediator> {
	public GoogleColleague(GoogleMediator mediator) {
		super(mediator);
	}

	public WebElement aLogin() {
		return visibilityXpath("//a[contains(@href,'https://mail.google.com/mail/')]");
	}

	public WebElement aGmail() {
		return visibilityXpath("//a[contains(@href,'https://accounts.google.com/ServiceLogin')]");
	}

	public WebElement inputLoginEmail() {
		return visibilityXpath("//input[@id='Email']");
	}

	public WebElement inputLoginPasswd() {
		return visibilityXpath("//input[@id='Passwd']");
	}

	public WebElement inputLoginBtn() {
		return visibilityXpath("//input[@id='signIn']");
	}
}
