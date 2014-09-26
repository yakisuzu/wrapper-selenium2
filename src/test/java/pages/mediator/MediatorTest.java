package pages.mediator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.colleague.google.GoogleColleague;
import pages.mediator.google.GoogleMediator;
import support.GenericUtils;
import support.function.ISupplier;
import support.js.TagBean;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static support.js.JsUtils.addElement;

public class MediatorTest {
	private static Logger LOG = LoggerFactory.getLogger(MediatorTest.class);
	private WebDriver dri;

	@Spy
	private GoogleMediator mediMock = new GoogleMediator();

	@Before
	public void setUp() throws Exception {
		LOG.info("Before is start");

		MockitoAnnotations.initMocks(this);
		dri = GenericUtils.getWebDriverList().get(0);
		mediMock.initializeDriver(dri);

		//protectedはspyできない
		//doReturn("").when(mediMock).initializeUrl();

		//private取得
		WebDriver dri = (WebDriver) Whitebox.getInternalState(mediMock, "driver");
		//ブランクに戻す
		if (mediMock.isIe()) {
			dri.navigate().back();
		} else {
			dri.navigate().to("");
		}

		LOG.info("Before is end");
	}

	@After
	public void tearDown() throws Exception {
		LOG.info("After is start");

		Thread.sleep(2000);
		dri.quit();

		LOG.info("After is end");
	}

	/**
	 * 1件取得
	 */
	@Test
	public void testFindElement001() {
		LOG.info("testFindElement001 is start");

		final String TAG = "div";
		final String ID1 = "id1";
		final String INNER1 = "inner1";
		ISupplier<String> initElement = new ISupplier<String>() {
			@Override
			public String get() {
				StringBuilder sb = new StringBuilder();
				TagBean bean1 = new TagBean(TAG);
				bean1.setId(ID1);
				bean1.setInnerHTML(INNER1);
				sb.append(addElement(bean1));
				return sb.toString();
			}
		};

		//test用element初期化
		mediMock.exeJs(initElement.get());

		GoogleColleague collMock = spy(mediMock.getColleague(GoogleColleague.class));
		doReturn(mediMock.findElement(By.xpath("//" + TAG + "[@id='" + ID1 + "']"))).when(collMock).aGmail();
		WebElement ida = collMock.aGmail();
		String text = ida.getAttribute("innerHTML");

		Assert.assertThat(INNER1, is(text));

		LOG.info("testFindElement001 is end");
	}

	/**
	 * 2件存在TimeoutExceptionエラー
	 */
	@Test
	public void testFindElement002() {
		LOG.info("testFindElement002 is start");

		final String TAG = "input";
		final String NAME1 = "name1";
		final String INNER1 = "inner1";
		final String INNER2 = "inner2";
		ISupplier<String> initElement = new ISupplier<String>() {
			@Override
			public String get() {
				StringBuilder sb = new StringBuilder();

				TagBean bean1 = new TagBean(TAG);
				bean1.setName(NAME1);
				bean1.setInnerHTML(INNER1);
				sb.append(addElement(bean1));

				TagBean bean2 = new TagBean(TAG);
				bean2.setName(NAME1);
				bean2.setInnerHTML(INNER2);
				sb.append(addElement(bean2));

				return sb.toString();
			}
		};

		//test用element初期化
		mediMock.exeJs(initElement.get());

		GoogleColleague collMock = spy(mediMock.getColleague(GoogleColleague.class));
		try {
			doReturn(mediMock.findElement(By.xpath("//input[@name='" + NAME1 + "']"))).when(collMock).aGmail();
			fail();
		} catch (TimeoutException e) {
			assertThat(e.getMessage(), is(matches("^element取得エラー")));
		}

		LOG.info("testFindElement002 is end");
	}
}