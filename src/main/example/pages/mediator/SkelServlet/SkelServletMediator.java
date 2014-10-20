package pages.mediator.SkelServlet;

import pages.colleague.Colleague;
import pages.colleague.SkelServlet.SkelServletColleague;
import pages.mediator.Mediator;
import support.properties.PageProperties;

import java.util.ArrayList;
import java.util.List;

public class SkelServletMediator extends Mediator {
	@Override
	protected String initializeUrl() {
		return PageProperties.getInstance().getString("skelservlet.url");
	}

	@Override
	protected List<Colleague> initializeColleagueList() {
		List<Colleague> ret = new ArrayList<>();
		ret.add(new SkelServletColleague(this));
		return ret;
	}
}
