package pages.mediator;

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
import pages.colleague.IColleague;
import scala.Tuple2;
import support.ProcessBuilderUtils;
import support.function.ISupplier;
import support.properties.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Mediator {
	protected abstract String initializeUrl();

	private static Logger LOG = LoggerFactory.getLogger(Mediator.class);
	private WebDriver driver;
	private Wait<WebDriver> wdriver;
	private Actions action;
	private Map<Class<? extends IColleague>, IColleague> colleagueMap;
	private int printNo;

	///////////////////////////////////////////////////
	//constructor
	///////////////////////////////////////////////////
	public Mediator() {
		colleagueMap = new HashMap<Class<? extends IColleague>, IColleague>();
		printNo = 1;
	}

	public void initializeDriver(WebDriver driver) {
		this.driver = driver;
		this.driver.navigate().to(initializeUrl());
		initializeSsl();

		wdriver = new WebDriverWait(driver, SystemProperties.getInstance().getInt("config.tryelementfindtime"));

		action = new Actions(driver);
	}

	private void initializeSsl() {
		if (isIe()) {
			if (getUrl().matches("^res:\\/\\/ieframe.dll\\/invalidcert.htm\\?SSLError=.*")) {
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			}
		}
	}

	///////////////////////////////////////////////////
	//for mediator
	///////////////////////////////////////////////////
	protected void addColleague(IColleague colleague) {
		colleagueMap.put(colleague.getClass(), colleague);
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

	public String exeJs(String js, Object... obj) {
		//objはarguments[0]でアクセスする
		//jsでreturnした文字列を戻り値として受ける
		return (String) ((JavascriptExecutor) driver).executeScript(js, obj);
	}

	///////////////////////////////////////////////////
	//for operators
	///////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public <T extends IColleague> T getColleague(Class<T> key) {
		return (T) colleagueMap.get(key);
	}

	public void quit() {
		driver.quit();
		// TODO ie以外
		if (isIe()) {
			ProcessBuilderUtils.killProcess("IEDriverServer.exe");
			ProcessBuilderUtils.killProcess("iexplore.exe");
		}
	}

	///////////////////////////////////////////////////
	//for colleague
	///////////////////////////////////////////////////
	public WebElement findElement(final By by) {
		List<WebElement> elementList = null;
		WebDriverException ex = null;
		for (ISupplier<ExpectedCondition<List<WebElement>>> func : getExpectedList(by)) {
			Tuple2<List<WebElement>, WebDriverException> elementListTuple = findElementList(func.get());
			elementList = elementListTuple._1;
			ex = elementListTuple._2;
			if (ex == null) {
				break;
			}
		}
		if (ex != null) {
			printScreen("ERROR_" + this.getClass().getName());
			throw ex;
		}

		assert elementList != null;
		if (elementList.size() > 1) {
			throw new NoSuchElementException("element複数件エラー");
		}
		return elementList.get(0);
	}

	private List<ISupplier<ExpectedCondition<List<WebElement>>>> getExpectedList(final By by) {
		List<ISupplier<ExpectedCondition<List<WebElement>>>> ret = new ArrayList<ISupplier<ExpectedCondition<List<WebElement>>>>();

		ret.add(new ISupplier<ExpectedCondition<List<WebElement>>>() {
			@Override
			public ExpectedCondition<List<WebElement>> get() {
				return ExpectedConditions.visibilityOfAllElementsLocatedBy(by);
			}
		});
		ret.add(new ISupplier<ExpectedCondition<List<WebElement>>>() {
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
				LOG.error(tryCnt + "回目 element取得エラー " + e.getClass().getName());

				if (tryCnt >= tryCntMax) {
					LOG.error(expectedList.toString() + " is fail");
					ex = e;
					break;
				}
			}
			tryCnt++;
		}
		return new Tuple2<List<WebElement>, WebDriverException>(elementList, ex);
	}

	public Actions getActions() {
		return action;
	}
}
