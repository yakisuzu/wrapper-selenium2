package operators.SkelServlet;

import operators.Operator;
import org.openqa.selenium.WebDriver;
import pages.colleague.SkelServlet.SkelServletColleague;
import pages.mediator.Mediator;
import pages.mediator.SkelServlet.SkelServletMediator;

public class SkelServletOperator extends Operator {
	public SkelServletOperator(WebDriver driver) {
		super(driver);
	}

	@Override
	protected Mediator initializeMediator() {
		return new SkelServletMediator();
	}

	private SkelServletColleague getSkelServletColleague() {
		return getColleague(SkelServletColleague.class);
	}

	public void clickWinOpen() {
		getSkelServletColleague();
	}
}
