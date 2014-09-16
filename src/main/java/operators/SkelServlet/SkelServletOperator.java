package operators.SkelServlet;

import operators.Operator;
import org.openqa.selenium.WebDriver;
import pages.colleague.SkelServlet.SkelServletColleague;
import pages.mediator.SkelServlet.SkelServletMediator;

public class SkelServletOperator extends Operator<SkelServletMediator> {
	public SkelServletOperator(WebDriver driver) {
		super(driver);
	}

	private SkelServletColleague coll1 = getColleague(SkelServletColleague.class);

	public void clickWinOpen() {
	}
}
