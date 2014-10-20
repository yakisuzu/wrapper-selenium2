package pages.mediator.google;

import pages.colleague.Colleague;
import pages.colleague.google.GoogleColleague;
import pages.colleague.google.GoogleMailColleague;
import pages.mediator.Mediator;
import support.properties.PageProperties;

import java.util.ArrayList;
import java.util.List;

public class GoogleMediator extends Mediator {
	@Override
	protected String initializeUrl() {
		return PageProperties.getInstance().getString("wwwgooglecojp.url");
	}

	@Override
	protected List<Colleague> initializeColleagueList() {
		List<Colleague> ret = new ArrayList<>();
		ret.add(new GoogleColleague(this));
		ret.add(new GoogleMailColleague(this));
		return ret;
	}
}
