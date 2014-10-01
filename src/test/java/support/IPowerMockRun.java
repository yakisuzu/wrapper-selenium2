package support;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"org.apache.*", "ch.qos.logback.*", "org.slf4j.*", "javax.xml.parsers.*", "org.w3c.*"})
public interface IPowerMockRun {
}
