package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericUtils {
	private static Logger LOG = LoggerFactory.getLogger(GenericUtils.class);

	public static List<WebDriver> getWebDriverList() {
		List<WebDriver> driverList = new ArrayList<WebDriver>();

		final String IE = "ie";
		final String FF = "ff";
		final String GC = "gc";
		//List<String> targetList = Arrays.asList(IE, FF, GC);
		List<String> targetList = Arrays.asList(FF);

		if (targetList.contains(IE)) {
			driverList.add(createWebDriver(new ISupplier<WebDriver>() {
				@Override
				public WebDriver get() {
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					return new InternetExplorerDriver(capabilities);
				}
			}));
		}

		if (targetList.contains(FF)) {
			driverList.add(createWebDriver(new ISupplier<WebDriver>() {
				@Override
				public WebDriver get() {
					return new FirefoxDriver();
				}
			}));
		}

		if (targetList.contains(GC)) {
			driverList.add(createWebDriver(new ISupplier<WebDriver>() {
				@Override
				public WebDriver get() {
					return new ChromeDriver();
				}
			}));
		}

		return driverList;
	}

	private static WebDriver createWebDriver(ISupplier<WebDriver> func) {
		String errorMsg = "WebDriver作成エラー";
		int tryCntMax = 3;

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
