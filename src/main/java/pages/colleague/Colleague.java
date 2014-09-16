package pages.colleague;

import org.openqa.selenium.By;
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

	protected WebElement visibilityXpath(String elementXpath) {
		return mediator.visibilityBy(By.xpath(elementXpath));
	}

	protected WebElement presenceXpath(String elementXpath) {
		return mediator.presenceBy(By.xpath(elementXpath));
	}
}
