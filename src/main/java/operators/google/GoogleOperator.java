package operators.google;

import operators.IFuncVoid;
import operators.Operator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.colleague.google.GoogleColleague;
import pages.colleague.google.GoogleMailColleague;
import pages.mediator.google.GoogleMediator;

public class GoogleOperator extends Operator<GoogleMediator> {
	public GoogleOperator(WebDriver driver) {
		super(driver);
	}

	private GoogleColleague coll1 = (GoogleColleague) getColleague(GoogleColleague.class);
	private GoogleMailColleague coll2 = (GoogleMailColleague) getColleague(GoogleMailColleague.class);

	public void enterLogin(final String email, final String pass) {
		execute(new IFuncVoid() {
			@Override
			public void accept() {
				coll1.aLogin().sendKeys(Keys.ENTER);
				coll1.inputLoginEmail().sendKeys(email);
				coll1.inputLoginPasswd().sendKeys(pass);
				coll1.inputLoginBtn().sendKeys(Keys.ENTER);
			}
		});
	}

	public void clickGmail() {
		execute(new IFuncVoid() {
			@Override
			public void accept() {
				coll2.aGmail().sendKeys(Keys.ENTER);
			}
		});
	}
}
