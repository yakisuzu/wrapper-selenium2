package operators.wwwgooglecojp;

import operators.supers.SuperOperators;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;

public class WwwGoogleCoJpOperator extends SuperOperators<WwwGoogleCoJpPage> {
	public WwwGoogleCoJpOperator(WwwGoogleCoJpPage page) {
		super(page);
	}

	public void clickLogin() {
		getPage().spanLogin().click();
	}

	public void clickGmail() {
		getPage().spanGmail().click();
	}
}
