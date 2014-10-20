package operators;

import org.openqa.selenium.WebDriver;
import pages.colleague.Colleague;
import pages.mediator.Mediator;

public abstract class Operator {
	protected abstract Mediator initializeMediator();

	//	private static Logger LOG = LoggerFactory.getLogger(Operator.class);
	private Mediator mediator;

	public Operator(WebDriver driver) {
		mediator = initializeMediator();
		mediator.initialize(driver);
	}

	public <T extends Colleague> T getColleague(Class<T> key) {
		return mediator.getColleague(key);
	}

	protected Mediator getMediator() {
		return mediator;
	}
}
