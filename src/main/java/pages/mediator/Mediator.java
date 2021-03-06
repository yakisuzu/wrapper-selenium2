package pages.mediator;

import com.google.common.base.Supplier;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.colleague.Colleague;
import scala.Tuple2;
import support.properties.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Mediator {
	protected abstract String initializeUrl();

	protected abstract List<Colleague> initializeColleagueList();

	private static Logger LOG = LoggerFactory.getLogger(Mediator.class);
	private WebDriver driver;
	private Wait<WebDriver> wdriver;
	private Actions action;
	private Map<Class<? extends Colleague>, Colleague> colleagueMap;
	private int printNo;

	///////////////////////////////////////////////////
	//constructor
	///////////////////////////////////////////////////
	public Mediator() {
		colleagueMap = new HashMap<>();
		printNo = 1;
	}

	public void initialize(WebDriver initDriver) {
		// driver
		driver = initDriver;
		wdriver = new WebDriverWait(initDriver, SystemProperties.getInstance().getInt("config.tryelementfindtime"));
		action = new Actions(initDriver);

		// colleague
		for (Colleague coll : initializeColleagueList()) {
			colleagueMap.put(coll.getClass(), coll);
		}

		// url
		driver.navigate().to(initializeUrl());

		// ssl
		if (isIe()) {
			if (getUrl().matches("^res://ieframe.dll/invalidcert.htm\\?SSLError=.*")) {
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			}
		}
	}

	///////////////////////////////////////////////////
	//for all
	///////////////////////////////////////////////////
	public boolean isIe() {
		return driver instanceof InternetExplorerDriver;
	}

	public boolean isFf() {
		return driver instanceof FirefoxDriver;
	}

	public boolean isGc() {
		return driver instanceof ChromeDriver;
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public String getHtml() {
		return driver.getPageSource();
	}

	protected File getScreenShotFile() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	protected byte[] getScreenShotByteArray() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public void printScreen(String fileName) {
		String path = "./printScreen/";
		String date = new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date());
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

		try {
			FileUtils.copyFile(getScreenShotFile(), new File(path + date + "_" + driverName + "_" + no + "_" + fileName + extension));
		} catch (IOException e) {
			LOG.error("スクリーンショットエラー", e);
		}
	}

	@SuppressWarnings("unchecked")
	public <R> R exeJs(String js, Object... obj) {
		//objはarguments[0]でアクセスする
		//戻り値はjsをreturnした値
		return (R) ((JavascriptExecutor) driver).executeScript(js, obj);
	}

	///////////////////////////////////////////////////
	//for operators
	///////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public <T extends Colleague> T getColleague(Class<T> key) {
		return (T) colleagueMap.get(key);
	}

	///////////////////////////////////////////////////
	//for colleague
	///////////////////////////////////////////////////
	public WebElement findElement(By by) {
		List<WebElement> elementList = null;
		WebDriverException ex = null;
		for (Supplier<ExpectedCondition<List<WebElement>>> func : getExpectedList(by)) {
			Tuple2<List<WebElement>, WebDriverException> elementListTuple = findElementList(func.get());
			elementList = elementListTuple._1;
			ex = elementListTuple._2;
			if (ex == null) {
				break;
			}
		}
		if (ex != null) {
			printScreen("ERROR_" + this.getClass().getName());
			LOG.error("-------------------------------------------------------");
			if (ex instanceof TimeoutException) {
				LOG.error(ex.getClass().getName() + " htmlとWebElement取得処理を再確認してください");
			}
			LOG.error(getHtml());
			throw ex;
		}

		assert elementList != null;
		if (elementList.size() > 1) {
			throw new NoSuchElementException("element複数件エラー");
		}
		return elementList.get(0);
	}

	private List<Supplier<ExpectedCondition<List<WebElement>>>> getExpectedList(final By by) {
		List<Supplier<ExpectedCondition<List<WebElement>>>> ret = new ArrayList<>();

		ret.add(new Supplier<ExpectedCondition<List<WebElement>>>() {
			@Override
			public ExpectedCondition<List<WebElement>> get() {
				return ExpectedConditions.visibilityOfAllElementsLocatedBy(by);
			}
		});
		ret.add(new Supplier<ExpectedCondition<List<WebElement>>>() {
			@Override
			public ExpectedCondition<List<WebElement>> get() {
				return ExpectedConditions.presenceOfAllElementsLocatedBy(by);
			}
		});

		return ret;
	}

	private Tuple2<List<WebElement>, WebDriverException> findElementList(ExpectedCondition<List<WebElement>> expectedList) {
		int tryCnt = 1;
		final int tryCntMax = SystemProperties.getInstance().getInt("config.tryelementfindcount");

		List<WebElement> elementList = null;
		WebDriverException ex = null;
		while (true) {
			try {
				elementList = wdriver.until(expectedList);
				break;
			} catch (WebDriverException e) {
				LOG.error("-------------------------------------------------------");
				LOG.error(tryCnt + "回目 element取得エラー " + e.getClass().getName());

				if (tryCnt >= tryCntMax) {
					LOG.error(expectedList.toString() + " is fail");
					ex = e;
					break;
				}
			}
			tryCnt++;
		}
		return new Tuple2<>(elementList, ex);
	}

	public Actions getActions() {
		return action;
	}
}
