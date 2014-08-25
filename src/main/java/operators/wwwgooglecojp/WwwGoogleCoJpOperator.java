package operators.wwwgooglecojp;

import operators.supers.SuperOperator;
import pages.wwwgooglecojp.WwwGoogleCoJpPage;

public class WwwGoogleCoJpOperator extends SuperOperator<WwwGoogleCoJpPage> {
	public WwwGoogleCoJpOperator(WwwGoogleCoJpPage page) {
		super(page);
	}

	public void clickLogin() {
		click(getPage().spanLogin());
	}

	public void clickGmail() {
		click(getPage().spanGmail());
	}
}
