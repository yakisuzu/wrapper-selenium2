package pages.mediator;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public abstract class Mediator {
	private WebDriver driver;
	private Wait<WebDriver> wdriver;

	protected abstract String initializeUrl();

	@SuppressWarnings({"Unused", "UnusedDeclaration"})
	private Mediator() {
	}

	private void initializeSsl() {
		// TODO conf ie
		if (getUrl().matches("res://______")) {
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
	}

	protected Mediator(Mediator page) {
		this.driver = page.driver;
		this.wdriver = page.wdriver;
	}

	protected Mediator(WebDriver driver) {
		this.driver = driver;
		this.driver.get(initializeUrl());
		initializeSsl();
		wdriver = new WebDriverWait(driver, 15);
	}

	protected WebElement findXpath(String elementXpath) {
		return wdriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
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

	public File getScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}
}
