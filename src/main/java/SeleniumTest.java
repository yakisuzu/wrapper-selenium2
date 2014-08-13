import org.openqa.selenium.firefox.FirefoxDriver;
import pages.GoogleTop;
import pages.SuperPage;

public class SeleniumTest {
	public static void main(String[] args) {
		SuperPage page =  new GoogleTop(new FirefoxDriver());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page.quit();
	}
}
