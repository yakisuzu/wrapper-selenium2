package pages.mediator;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.colleague.Colleague;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Mediator {
	private WebDriver driver;
	private Wait<WebDriver> wdriver;
	private Map<Class<Colleague<? extends Mediator>>, Colleague<? extends Mediator>> colleagueMap//
			= new HashMap<Class<Colleague<? extends Mediator>>, Colleague<? extends Mediator>>();

	protected abstract String initializeUrl();

	public void initializeDriver(WebDriver driver) {
		this.driver = driver;
		this.driver.navigate().to(initializeUrl());
		initializeSsl();

		wdriver = new WebDriverWait(driver, 15);
	}

	private void initializeSsl() {
		// TODO conf ie
		if (getUrl().matches("res://______")) {
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
	}

	protected <T extends Mediator> void addColleague(Colleague<T> colleague) {
		colleagueMap.put((Class<Colleague<? extends Mediator>>) colleague.getClass(), colleague);
	}

	public <T extends Mediator> Colleague<? extends Mediator> getColleague(Class<Colleague<T>> key) {
		return colleagueMap.get(key);
	}

	public WebElement findXpath(String elementXpath) {
		return wdriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
	}

	public File getScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
