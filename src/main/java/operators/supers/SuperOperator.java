package operators.supers;

import org.openqa.selenium.NoSuchElementException;
import pages.supers.AbstractSuperPage;

public class SuperOperator<T extends AbstractSuperPage> {
	private T page;
	private boolean errorFlg = false;

	public SuperOperator(T page) {
		this.page = page;
	}

	public boolean getErrorFlg() {
		return errorFlg;
	}

	protected T getPage() {
		return page;
	}

	protected void execute(FuncVoid func) {
		try {
			func.accept();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			errorFlg = true;
		}
	}
}
