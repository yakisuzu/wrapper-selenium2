package operators.supers;

import org.apache.commons.io.FileUtils;
import pages.supers.AbstractSuperPage;

import java.io.File;
import java.io.IOException;

public class SuperOperator<T extends AbstractSuperPage> {
	private T page;
	private boolean errorFlg = false;
	private int printNo = 1;

	public SuperOperator(T page) {
		this.page = page;
	}

	protected T getPage() {
		return page;
	}

	protected void execute(IFuncVoid func) {
		try {
			func.accept();
		} catch (Exception e) {
			e.printStackTrace();
			printScreen("ERROR_" + page.getClass().getName());
			errorFlg = true;
		}
	}

	public boolean getErrorFlg() {
		return errorFlg;
	}

	public void printScreen(String fileName) {
		String path = "./printScreen/";
		String num = "_" + String.format("%1$04d", printNo++);
		String extension = ".bmp";
		try {
			FileUtils.copyFile(page.getScreenshot(), new File(path + fileName + num + extension));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void quit() {
		page.quit();
	}
}
