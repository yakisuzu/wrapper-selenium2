package pages.mediator.google;

		import org.openqa.selenium.WebDriver;
		import pages.mediator.Mediator;

public class GoogleMailMediator extends GoogleMediator {
	public GoogleMailMediator(Mediator page){
		super(page);
	}

	public GoogleMailMediator(WebDriver driver) {
		super(driver);
	}
}
