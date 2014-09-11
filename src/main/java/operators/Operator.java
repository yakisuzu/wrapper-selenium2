package operators;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.colleague.Colleague;
import pages.mediator.Mediator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Operator<T extends Mediator> {
	private static Logger LOG = LoggerFactory.getLogger(Operator.class);
	private T mediator;
	private boolean errorFlg = false;
	private int printNo = 1;

	public Operator(WebDriver driver) {
		String errorMsg = "mediator作成エラー";

		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] actualTypeArray = pt.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArray[0];
		try {
			mediator = entityClass.newInstance();
		} catch (InstantiationException e) {
			LOG.error(errorMsg, e);
		} catch (IllegalAccessException e) {
			LOG.error(errorMsg, e);
		}

		mediator.initializeDriver(driver);
	}

	public Colleague<? extends Mediator> getColleague(Class<? extends Colleague<? extends Mediator>> key){
		return mediator.getColleague((Class<Colleague<T>>) key);
	}

	protected void execute(IFuncVoid func) {
		try {
			func.accept();
		} catch (Exception e) {
			LOG.error("実行エラー", e);
			printScreen("ERROR_" + mediator.getClass().getName());
			errorFlg = true;
		}
	}

	public boolean getErrorFlg() {
		return errorFlg;
	}

	public void printScreen(String fileName) {
		String path = "./printScreen/";
		String no = "_" + String.format("%1$04d", printNo++);
		String extension = ".bmp";
		try {
			FileUtils.copyFile(mediator.getScreenshot(), new File(path + fileName + no + extension));
		} catch (IOException e) {
			LOG.error("スクリーンショットエラー", e);
		}
	}

	public void quit() {
		mediator.quit();
	}
}
