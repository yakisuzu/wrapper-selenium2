package pages.mediator.SkelServlet;

import pages.colleague.SkelServlet.SkelServletColleague;
import pages.mediator.Mediator;
import support.properties.PageProperties;

public class SkelServletMediator extends Mediator {
	public SkelServletMediator() {
		super();
		addColleague(new SkelServletColleague(this));
	}

	@Override
	protected String initializeUrl() {
		return PageProperties.getInstance().getProperty("skelservlet.url");
	}
}
