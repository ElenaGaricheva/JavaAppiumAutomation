package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
