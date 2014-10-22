package pages.mediator;

import operators.Operator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.colleague.Colleague;
import support.GenericUtils;
import support.ProcessBuilderUtils;
import support.js.TagBean;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static support.js.JsUtils.addElement;

public class MediatorTest {
	private static Logger LOG = LoggerFactory.getLogger(MediatorTest.class);
	private static WebDriver dri = GenericUtils.getWebDriverList().get(0);
	@SuppressWarnings("ConstantConditions")
	private static String TEST_PAGE = MediatorTest.class.getClassLoader().getResource("testPage.html").toString();

	private TestOperator ope;

	/**
	 * Operator
	 */
	private class TestOperator extends Operator {
		public TestOperator(WebDriver driver) {
			super(driver);
		}

		@Override
		protected Mediator initializeMediator() {
			return new TestMediator();
		}

		public TestColleague getTestColleague() {
			return getColleague(TestColleague.class);
		}

		public void exeJsAddElement(TagBean... beanArray) {
			getMediator().exeJs(addElement(beanArray));
		}

		public WebElement getElement() {
			return null;
		}
	}

	/**
	 * Mediator
	 */
	private class TestMediator extends Mediator {
		@Override
		protected String initializeUrl() {
			return TEST_PAGE;
		}

		@Override
		protected List<Colleague> initializeColleagueList() {
			List<Colleague> ret = new ArrayList<>();
			ret.add(new TestColleague(this));
			return ret;
		}
	}

	/**
	 * Colleague
	 */
	private class TestColleague extends Colleague {
		public TestColleague(Mediator mediator) {
			super(mediator);
		}

		public WebElement getElement001() {
			return findXpath("//div[@id='id1']");
		}

		public WebElement getElement002() {
			return findXpath("//input[@name='name1']");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dri.quit();
		ProcessBuilderUtils.killProcessIe();
	}

	@Before
	public void setUp() throws Exception {
		ope = Mockito.spy(new TestOperator(dri));
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(2000);
	}

	/**
	 * 1件取得
	 */
	@Test
	public void testFindElement001() {
		LOG.info("start");

		final HTML.Tag TAG = HTML.Tag.DIV;
		final String ID1 = "id1";
		final String INNER1 = "inner1";

		//test用element初期化
		{
			TagBean bean1 = new TagBean(TAG);
			bean1.putAttributeMap(HTML.Attribute.ID, ID1);
			bean1.setInnerHTML(INNER1);

			ope.exeJsAddElement(bean1);
		}

		//spy
		{
			WebElement ret = ope.getTestColleague().getElement001();
			doReturn(ret).when(ope).getElement();
		}
		String text = ope.getElement().getAttribute("innerHTML");

		assertThat(INNER1, is(text));

		LOG.info("end");
	}

	/**
	 * 2件存在NoSuchElementExceptionエラー
	 */
	@Test
	public void testFindElement002() {
		LOG.info("start");

		final HTML.Tag TAG = HTML.Tag.INPUT;
		final String TAG_TYPE = "text";
		final String NAME1 = "name1";
		final String VALUE1 = "text1";
		final String VALUE2 = "text2";

		//test用element初期化
		{
			TagBean bean1 = new TagBean(TAG);
			bean1.putAttributeMap(HTML.Attribute.TYPE, TAG_TYPE);
			bean1.putAttributeMap(HTML.Attribute.NAME, NAME1);
			bean1.putAttributeMap(HTML.Attribute.VALUE, VALUE1);

			TagBean bean2 = new TagBean(TAG);
			bean2.putAttributeMap(HTML.Attribute.TYPE, TAG_TYPE);
			bean2.putAttributeMap(HTML.Attribute.NAME, NAME1);
			bean2.putAttributeMap(HTML.Attribute.VALUE, VALUE2);

			ope.exeJsAddElement(bean1, bean2);
		}

		try {
			ope.getTestColleague().getElement002();
			fail();
		} catch (Exception e) {
			LOG.info(e.getMessage());
			assertThat(e, is(instanceOf(NoSuchElementException.class)));
			assertThat(e.getMessage().startsWith("element複数件エラー"), is(true));
		}

		LOG.info("end");
	}
}