package pages.supers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractSuperPage {

	private WebDriver driver;

	protected abstract String initializeUrl();

	@SuppressWarnings({"Unused", "UnusedDeclaration"})
	private AbstractSuperPage() {
	}

	private void initializeSsl() {
		// TODO conf ie
		if (getUrl().matches("ref______")) {
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
	}

	protected AbstractSuperPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(initializeUrl());
		initializeSsl();
	}

	protected WebElement findTag(WebElement ele, String elementTag) {
		return ele.findElement(By.tagName(elementTag));
	}

	protected WebElement findId(String elementId) {
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
