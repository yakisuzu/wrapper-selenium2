package operators.wwwgooglecojp;

import operators.supers.SuperOperator;
import org.openqa.selenium.Keys;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;

public class WwwGoogleCoJpOperator extends SuperOperator<WwwGoogleCoJpPage> {
	public WwwGoogleCoJpOperator(WwwGoogleCoJpPage page) {
		super(page);
	}

	public void clickLogin() {
		getPage().spanLogin().sendKeys(Keys.ENTER);
	}

	public void clickGmail() {
		getPage().spanGmail().sendKeys(Keys.ENTER);
	}
}
