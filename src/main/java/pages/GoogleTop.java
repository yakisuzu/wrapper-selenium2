package pages;

import org.openqa.selenium.WebDriver;

public class GoogleTop extends SuperPage {
	public GoogleTop(WebDriver driver) {
		super(driver);
		setUrl("http://www.google.co.jp/");
	}
}
