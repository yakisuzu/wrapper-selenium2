package operators.wwwgooglecojp;

import operators.supers.IFuncVoid;
import operators.supers.SuperOperator;
import org.openqa.selenium.Keys;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;

public class WwwGoogleCoJpOperator extends SuperOperator<WwwGoogleCoJpPage> {
	public WwwGoogleCoJpOperator(WwwGoogleCoJpPage page) {
		super(page);
	}

	public void enterLogin(final String email, final String pass) {
		execute(new IFuncVoid() {
			@Override
			public void accept() {
				getPage().inputLoginEmail().sendKeys(email);

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
