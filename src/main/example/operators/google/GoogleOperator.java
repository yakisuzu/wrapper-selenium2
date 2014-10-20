package operators.google;

import operators.Operator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.colleague.google.GoogleColleague;
import pages.colleague.google.GoogleMailColleague;
import pages.mediator.Mediator;
import pages.mediator.google.GoogleMediator;

public class GoogleOperator extends Operator {
	public GoogleOperator(WebDriver driver) {
		super(driver);
	}

	@Override
	protected Mediator initializeMediator() {
		return new GoogleMediator();
	}

	private GoogleColleague getGoogleColleague() {
		return getColleague(GoogleColleague.class);
	}

	private GoogleMailColleague getGoogleMailColleague() {
		return getColleague(GoogleMailColleague.class);
	}

	public void enterLogin(String email, String pass) {
		getGoogleColleague().aLogin().sendKeys(Keys.ENTER);
		getGoogleColleague().inputLoginEmail().sendKeys(email);
		getGoogleColleague().inputLoginPasswd().sendKeys(pass);
		//getGoogleColleague().inputLoginBtn().sendKeys(Keys.ENTER);
	}

	public void clickGmail() {
		getGoogleMailColleague().aGmail().sendKeys(Keys.ENTER);
	}
}
