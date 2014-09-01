package operators.supers;

import pages.supers.AbstractSuperPage;
import support.PageProperties;

public class SuperOperator<T extends AbstractSuperPage> {
	private T page;

	public SuperOperator(T page) {
		this.page = page;
	}

	protected T getPage() {
		return page;
	}

	protected PageProperties getPageProperties() {
		return PageProperties.getInstance();
	}
}
