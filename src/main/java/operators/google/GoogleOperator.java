package operators.google;

import operators.IFuncVoid;
import operators.Operator;
import org.openqa.selenium.Keys;
import pages.mediator.google.GoogleMediator;

public class GoogleOperator extends Operator<GoogleMediator> {
	public GoogleOperator(GoogleMediator page) {
		super(page);
	}

	public void enterLogin(final String email, final String pass) {
		execute(new IFuncVoid() {
			@Override
			public void accept() {
				getPage().aLogin().sendKeys(Keys.ENTER);
				getPage().inputLoginEmail().sendKeys(email);
				getPage().inputLoginPasswd().sendKeys(pass);
				getPage().inputLoginBtn().sendKeys(Keys.ENTER);
			}
		});
	}

	public void clickGmail() {
		execute(new IFuncVoid() {
			@Override
			public void accept() {
				getPage().aGmail().sendKeys(Keys.ENTER);
			}
		});
	}
}
