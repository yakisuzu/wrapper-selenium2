package operators.wwwgooglecojp;

import operators.supers.FuncVoid;
import operators.supers.SuperOperator;
import org.openqa.selenium.Keys;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;

public class WwwGoogleCoJpOperator extends SuperOperator<WwwGoogleCoJpPage> {
	public WwwGoogleCoJpOperator(WwwGoogleCoJpPage page) {
		super(page);
	}

	public void clickLogin() {
		execute(new FuncVoid() {
			@Override
			public void accept() {
				getPage().aLogin().sendKeys(Keys.ENTER);
			}
		});
	}

	public void clickGmail() {
		execute(new FuncVoid() {
			@Override
			public void accept() {
				getPage().aGmail().sendKeys(Keys.ENTER);
			}
		});
	}
}
