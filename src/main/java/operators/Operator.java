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

		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] actualTypeArray = pt.getActualTypeArguments();
		@SuppressWarnings("unchecked")
		Class<M> entityClass = (Class<M>) actualTypeArray[0];
		try {
			mediator = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LOG.error("mediator作成エラー", e);
		}

		mediator.initializeDriver(driver);
	}

	public void getPrint() {
		mediator.printScreen(this.getClass().getName());
	}

	public <T extends IColleague> T getColleague(Class<T> key) {
		return mediator.getColleague(key);
	}
}
