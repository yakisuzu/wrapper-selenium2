package pages.mediator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.colleague.IColleague;
import support.ProcessBuilderUtils;
import support.properties.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Mediator {
	protected abstract String initializeUrl();

	private static Logger LOG = LoggerFactory.getLogger(Mediator.class);
	private WebDriver driver;
	private Wait<WebDriver> wdriver;
	private Map<Class<? extends IColleague>, IColleague> colleagueMap;
	private int printNo;

	public Mediator() {
		colleagueMap = new HashMap<Class<? extends IColleague>, IColleague>();
		printNo = 1;
	}

	public void initializeDriver(WebDriver driver) {
		this.driver = driver;
		this.driver.navigate().to(initializeUrl());
		initializeSsl();

		wdriver = new WebDriverWait(driver, SystemProperties.getInstance().getInt("config.tryelementfindtime"));
	}

	private void initializeSsl() {
		if (getUrl().matches("^res:\\/\\/ieframe.dll\\/invalidcert.htm\\?SSLError=.*")) {
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

	public WebElement visibilityBy(By by) {
		return findElement(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	public WebElement presenceBy(By by) {
		return findElement(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	private WebElement findElement(ExpectedCondition<List<WebElement>> expectedList) {
		List<WebElement> elementList = findElementList(1, expectedList);
		if (elementList.isEmpty()) {
			throw new ElementNotVisibleException("element0件エラー");
		} else if (elementList.size() > 1) {
			throw new ElementNotVisibleException("element2件以上エラー");
		}
		return elementList.get(0);
	}

	private List<WebElement> findElementList(int exeCnt, ExpectedCondition<List<WebElement>> expectedList) {
		final int exeCntMax = SystemProperties.getInstance().getInt("config.tryelementfindcount");

		List<WebElement> elementList = null;
		try {
			elementList = wdriver.until(expectedList);
		} catch (NoSuchElementException e) {
			if (exeCntMax >= exeCnt) {
				LOG.error("element取得エラー", e);
				printScreen("ERROR_" + this.getClass().getName());
				throw e;
			}
			findElementList(exeCnt + 1, expectedList);
		}
		return elementList;
	}

	public void printScreen(String fileName) {
		String path = "./printScreen/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
		String date = sdf.format(new Date());
		String driverName = "";
		if (isIe()) {
			driverName = SystemProperties.getInstance().getString("webdriver.ie");
		} else if (isFf()) {
			driverName = SystemProperties.getInstance().getString("webdriver.ff");
		} else if (isGc()) {
			driverName = SystemProperties.getInstance().getString("webdriver.gc");
		}
		String no = String.format("%1$04d", printNo++);
		String extension = ".bmp";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(screenshot, new File(path + fileName + "_" + date + "_" + driverName + "_" + no + extension));
		} catch (IOException e) {
			LOG.error("スクリーンショットエラー", e);
		}
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
