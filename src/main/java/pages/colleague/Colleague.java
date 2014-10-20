package pages.colleague;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.mediator.Mediator;

public class Colleague {
	private Mediator mediator;

	public Colleague(Mediator mediator) {
		this.mediator = mediator;
	}

	protected WebElement findXpath(String elementXpath) {
		return mediator.findElement(By.xpath(elementXpath));
	}

	protected Actions getActions() {
		return mediator.getActions();
	}

	protected void run(Actions action) {
		action.build().perform();
	}
}
