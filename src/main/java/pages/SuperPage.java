package pages;

import org.openqa.selenium.WebDriver;

public class SuperPage {

	WebDriver driver;

	private SuperPage(){
	}

	public SuperPage(WebDriver driver){
		this.driver = driver;
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	protected void setUrl(String url) {
		driver.get(url);
	}

	public void quit(){
		driver.quit();
	}
}
