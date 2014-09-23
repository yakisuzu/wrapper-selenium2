package pages.mediator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.openqa.selenium.By;
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
		//url変更
		dri.navigate().to("");

		LOG.info("Before is end");
	}

	@After
	public void tearDown() throws Exception {
		LOG.info("After is start");

		Thread.sleep(2000);
		dri.quit();

		LOG.info("After is end");
	}

	@Test
	public void testFindElement1() throws Exception {
		LOG.info("testFindElement1 is start");

		ISupplier<String> initElement = new ISupplier<String>() {
			@Override
			public String get() {
				TagBean bean = new TagBean("div");
				bean.setId("ida");
				bean.setInnerHTML("inner");
				return addElement(bean);
			}
		};

		//test用element初期化
		mediMock.exeJs(initElement.get());

		GoogleColleague collMock = spy(mediMock.getColleague(GoogleColleague.class));
		doReturn(mediMock.findElement(By.xpath("//div[@id='ida']"))).when(collMock).aGmail();
		WebElement ida = collMock.aGmail();

		String text = ida.getAttribute("innerHTML");

		Assert.assertThat("inner", is(text));

		LOG.info("testFindElement1 is end");
	}

	@Test
	public void testFindElement2() {
		LOG.info("testFindElement2 is start");
		LOG.info("testFindElement2 is end");
	}
}