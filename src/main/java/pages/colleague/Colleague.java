package pages.colleague;

import org.openqa.selenium.WebElement;
import pages.mediator.Mediator;

public class Colleague<T extends Mediator> {
	private T mediator;

	public Colleague(T mediator) {
		this.mediator = mediator;
	}

	protected WebElement findXpath(String elementXpath) {
		return mediator.findXpath(elementXpath);
	}
}
