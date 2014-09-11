package pages.colleague.google;

import org.openqa.selenium.WebDriver;
import pages.mediator.Mediator;

public class GoogleMailColleague extends GoogleColleague {
	public GoogleMailColleague(Mediator page){
		super(page);
	}

	public GoogleMailColleague(WebDriver driver) {
		super(driver);
	}
}
