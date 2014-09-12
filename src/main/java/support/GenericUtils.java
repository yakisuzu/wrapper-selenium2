package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GenericUtils {
	private static Logger LOG = LoggerFactory.getLogger(GenericUtils.class);

	public static List<WebDriver> getWebDriverList() {
		List<WebDriver> driverList = new ArrayList<WebDriver>();
		driverList.add(createWebDriver(new ISupplier<WebDriver>() {
			@Override
			public WebDriver get() {
				return new FirefoxDriver();
			}
		}));
//		driverList.add(createWebDriver(new ISupplier<WebDriver>() {
//			@Override
//			public WebDriver get() {
//				return new ChromeDriver();
//			}
//		}));
//		driverList.add(createWebDriver(new ISupplier<WebDriver>() {
//			@Override
//			public WebDriver get() {
//				return new InternetExplorerDriver();
//			}
//		}));
		return driverList;
	}

	private static WebDriver createWebDriver(ISupplier<WebDriver> func) {
		String errorMsg = "WebDriver作成エラー";
		int tryCntMax = 5;

		for (int tryCnt = 1; tryCnt <= tryCntMax; tryCnt++) {
			WebDriver dri = null;
			Throwable t = null;
			try {
				dri = func.get();
			} catch (SessionNotFoundException e) {
				t = e;
			} catch (WebDriverException e) {
				t = e;
			}

			if (t == null) {
				return dri;
			}

			LOG.error(errorMsg + " " + tryCnt + "回目", t);
		}
		throw new WebDriverException(errorMsg);
	}
}
