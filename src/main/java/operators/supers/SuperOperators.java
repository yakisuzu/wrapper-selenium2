package operators.supers;

import pages.supers.AbstractSuperPage;

public class SuperOperators<T extends AbstractSuperPage> {
	private T page;

	public SuperOperators(T page) {
		this.page = page;
	}

	protected T getPage() {
		return page;
	}
}
