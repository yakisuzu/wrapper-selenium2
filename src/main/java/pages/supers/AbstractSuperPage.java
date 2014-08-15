package pages.supers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractSuperPage {

	private WebDriver driver;

	@SuppressWarnings("UnusedDeclaration")
	private AbstractSuperPage() {
	}

	protected abstract String initializeUrl();

	public AbstractSuperPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(initializeUrl());
	}

	public WebElement findId(String elementId) {
		return driver.findElement(By.id(elementId));
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public String getHtml() {
		return driver.getPageSource();
	}

	public void quit() {
		driver.quit();
	}
}
