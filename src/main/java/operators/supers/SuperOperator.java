package operators.supers;

import org.openqa.selenium.WebElement;
import pages.supers.AbstractSuperPage;

public class SuperOperator<T extends AbstractSuperPage> {
	private T page;

	public SuperOperator(T page) {
		this.page = page;
	}

	protected T getPage() {
		return page;
	}

	protected void click(WebElement ele) {
		ele.sendKeys("¥n");
	}
}
