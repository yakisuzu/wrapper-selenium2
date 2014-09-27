package support;

import com.google.common.base.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import support.properties.SystemProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericUtils {
	private static Logger LOG = LoggerFactory.getLogger(GenericUtils.class);

	public static List<WebDriver> getWebDriverList() {
		List<WebDriver> driverList = new ArrayList<>();

		final String IE = SystemProperties.getInstance().getString("webdriver.ie");
		final String FF = SystemProperties.getInstance().getString("webdriver.ff");
		final String GC = SystemProperties.getInstance().getString("webdriver.gc");
		final String[] initList = SystemProperties.getInstance().getString("init.webdriverlist").split(",");
		final List<String> targetList = Arrays.asList(initList);

		if (targetList.contains(IE)) {
			driverList.add(createWebDriver(new Supplier<WebDriver>() {
				@Override
				public WebDriver get() {
//					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//					return new InternetExplorerDriver(capabilities);
					return new InternetExplorerDriver();
				}
			}));
		}

		if (targetList.contains(FF)) {
			driverList.add(createWebDriver(new Supplier<WebDriver>() {
				@Override
				public WebDriver get() {
					return new FirefoxDriver();
				}
			}));
		}

		if (targetList.contains(GC)) {
			driverList.add(createWebDriver(new Supplier<WebDriver>() {
				@Override
				public WebDriver get() {
					return new ChromeDriver();
				}
			}));
		}

		return driverList;
	}

	private static WebDriver createWebDriver(Supplier<WebDriver> func) {
		int tryCnt = 1;
		final int tryCntMax = SystemProperties.getInstance().getInt("config.trywebdrivercreatecount");

		WebDriver dri;
		while (true) {
			try {
				dri = func.get();
				break;
			} catch (WebDriverException e) {
				LOG.error(tryCnt + "回目 WebDriver作成エラー ", e);

				if (tryCnt >= tryCntMax) {
					throw e;
				}
			}
			tryCnt++;
		}
		return dri;
	}
}
