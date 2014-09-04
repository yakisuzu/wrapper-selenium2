package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class GenericUtils {
	public static List<WebDriver> getWebDriverList() {
		List<WebDriver> driverList = new ArrayList<WebDriver>();
		try {
			driverList.add(new FirefoxDriver());
			//driverList.add(new ChromeDriver());
			//driverList.add(new InternetExplorerDriver());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driverList;
	}
}
