package pages.mediator.google;

import pages.colleague.google.GoogleColleague;
import pages.colleague.google.GoogleMailColleague;
import pages.mediator.Mediator;
import support.PageProperties;

public class GoogleMediator extends Mediator {
	public GoogleMediator(){
		super();
		addColleague(new GoogleColleague(this));
		addColleague(new GoogleMailColleague(this));
	}

	@Override
	protected String initializeUrl() {
		return PageProperties.getInstance().getProperty("wwwgooglecojp.url");
	}
}
