package pages.mediator;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.colleague.IColleague;
import support.ProcessBuilderUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Mediator {
	private WebDriver driver;
	private Wait<WebDriver> wdriver;
	private Map<Class<? extends IColleague>, IColleague> colleagueMap;

	protected abstract String initializeUrl();

	public void initializeDriver(WebDriver driver) {
		this.driver = driver;
		this.driver.navigate().to(initializeUrl());
		initializeSsl();

		wdriver = new WebDriverWait(driver, 5);
		colleagueMap = new HashMap<Class<? extends IColleague>, IColleague>();
	}

	private void initializeSsl() {
		// TODO conf ie
		if (getUrl().matches("res://______")) {
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
	}

	protected void addColleague(IColleague colleague) {
		colleagueMap.put(colleague.getClass(), colleague);
	}

	@SuppressWarnings("unchecked")
	public <T extends IColleague> T getColleague(Class<T> key) {
		return (T) colleagueMap.get(key);
	}

	public boolean isIe() {
		return driver instanceof InternetExplorerDriver;
	}

	public boolean isFf() {
		return driver instanceof FirefoxDriver;
	}

	public boolean isGc() {
		return driver instanceof ChromeDriver;
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
		//todo
		if (isIe()) {
			ProcessBuilderUtils.killProcess("IEDriverServer.exe");
			ProcessBuilderUtils.killProcess("iexplore.exe");
		}
	}
}
