package pages.mediator;

import com.google.common.base.Supplier;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.colleague.google.GoogleColleague;
import pages.mediator.google.GoogleMediator;
import support.GenericUtils;
import support.ProcessBuilderUtils;
import support.js.TagBean;

import javax.swing.text.html.HTML;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static support.js.JsUtils.addElement;

public class MediatorTest {
	private static Logger LOG = LoggerFactory.getLogger(MediatorTest.class);
	private WebDriver dri;

	@Spy
	private GoogleMediator mediMock = new GoogleMediator();

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ProcessBuilderUtils.killProcessIe();
	}

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
	@Ignore
	@Test
	public void testFindElement001() {
		LOG.info("testFindElement001 is start");

		final HTML.Tag TAG = HTML.Tag.DIV;
		final String ID1 = "id1";
		final String INNER1 = "inner1";
		Supplier<String> initElement = new Supplier<String>() {
			@Override
			public String get() {
				StringBuilder sb = new StringBuilder();

				TagBean bean1 = new TagBean(TAG);
				bean1.putAttributeMap(HTML.Attribute.ID, ID1);
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

		assertThat(INNER1, is(text));

		LOG.info("testFindElement001 is end");
	}

	/**
	 * 2件存在NoSuchElementExceptionエラー
	 */
	@Test
	public void testFindElement002() {
		LOG.info("testFindElement002 is start");

		final HTML.Tag TAG = HTML.Tag.INPUT;
		final String TAG_TYPE = "text";
		final String NAME1 = "name1";
		final String VALUE1 = "text1";
		final String VALUE2 = "text2";
		Supplier<String> initElement = new Supplier<String>() {
			@Override
			public String get() {
				StringBuilder sb = new StringBuilder();

				TagBean bean1 = new TagBean(TAG);
				bean1.putAttributeMap(HTML.Attribute.TYPE, TAG_TYPE);
				bean1.putAttributeMap(HTML.Attribute.NAME, NAME1);
				bean1.putAttributeMap(HTML.Attribute.VALUE, VALUE1);
				sb.append(addElement(bean1));

				TagBean bean2 = new TagBean(TAG);
				bean1.putAttributeMap(HTML.Attribute.TYPE, TAG_TYPE);
				bean2.putAttributeMap(HTML.Attribute.NAME, NAME1);
				bean2.putAttributeMap(HTML.Attribute.VALUE, VALUE2);
				sb.append(addElement(bean2));

				return sb.toString();
			}
		};

		//test用element初期化
		mediMock.exeJs(initElement.get());

		GoogleColleague collMock = spy(mediMock.getColleague(GoogleColleague.class));
		try {
			doReturn(mediMock.findElement(By.xpath("//" + TAG + "[@name='" + NAME1 + "']"))).when(collMock).aGmail();
			fail();
		} catch (Exception e) {
			LOG.info(e.getMessage());
			assertThat(e, is(instanceOf(NoSuchElementException.class)));
			assertThat(e.getMessage().startsWith("element複数件エラー"), is(true));
		}

		LOG.info("testFindElement002 is end");
	}
}