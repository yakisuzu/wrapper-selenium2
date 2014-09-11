package operators;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mediator.Mediator;

import java.io.File;
import java.io.IOException;

public class Operator<T extends Mediator> {
	private static Logger LOG = LoggerFactory.getLogger(Operator.class);
	private T page;
	private boolean errorFlg = false;
	private int printNo = 1;

	public Operator(T page) {
		this.page = page;
	}

	protected T getPage() {
		return page;
	}

	protected void execute(IFuncVoid func) {
		try {
			func.accept();
		} catch (Exception e) {
			LOG.error("実行エラー", e);
			printScreen("ERROR_" + page.getClass().getName());
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
			FileUtils.copyFile(page.getScreenshot(), new File(path + fileName + no + extension));
		} catch (IOException e) {
			LOG.error("スクリーンショットエラー", e);
		}
	}

	public void quit() {
		page.quit();
	}
}
