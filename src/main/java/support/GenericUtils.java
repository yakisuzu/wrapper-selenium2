package support;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GenericUtils {
	private static Logger LOG = LoggerFactory.getLogger(GenericUtils.class);

	public static List<WebDriver> getWebDriverList() {
		List<WebDriver> driverList = new ArrayList<WebDriver>();
		try {
			driverList.add(new FirefoxDriver());
			//driverList.add(new ChromeDriver());
			//driverList.add(new InternetExplorerDriver());
		} catch (SessionNotCreatedException e) {
			LOG.error("session作成エラー", e);
		}
		return driverList;
	}
}
