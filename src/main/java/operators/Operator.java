package operators;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.colleague.IColleague;
import pages.mediator.Mediator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Operator<M extends Mediator> {
	private static Logger LOG = LoggerFactory.getLogger(Operator.class);
	private M mediator;

	private Operator() {
	}

	public Operator(WebDriver driver) {
		this();
		String errorMsg = "mediator作成エラー";

		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] actualTypeArray = pt.getActualTypeArguments();
		@SuppressWarnings("unchecked")
		Class<M> entityClass = (Class<M>) actualTypeArray[0];
		try {
			mediator = entityClass.newInstance();
		} catch (InstantiationException e) {
			LOG.error(errorMsg, e);
		} catch (IllegalAccessException e) {
			LOG.error(errorMsg, e);
		}

		mediator.initializeDriver(driver);
	}

	public <T extends IColleague> T getColleague(Class<T> key) {
		return mediator.getColleague(key);
	}

	public void quit() {
		mediator.quit();
	}
}
