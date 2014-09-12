package pages.colleague;

import org.openqa.selenium.WebElement;
import pages.mediator.Mediator;

public class Colleague<T extends Mediator> implements IColleague {
	private T mediator;

	private Colleague() {
	}

	public Colleague(T mediator) {
		this();
		this.mediator = mediator;
	}

	protected WebElement findXpath(String elementXpath) {
		return mediator.findXpath(elementXpath);
	}
}
