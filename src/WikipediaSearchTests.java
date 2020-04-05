import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class WikipediaSearchTests {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        //Приложение установлено на эмуляторе через Google Play. Скачанное по ссылке падает при запуске.
        //capabilities.setCapability("app", "/Users/elenagariceva/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchArticlesTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Button for setup skip not found");

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Search container not found");

        waitForTextElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Expected text not found"
        );

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_plate"),
                "JAVA",
                "Search field not found");

        //В установленной мной версии приложения(последней) нет id контейнера у позиции результата поиска
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']/..//*[@text = 'Object-oriented programming language']"),
                "not found"
        );
    }

    @Test
    public void cancelSearchArticlesTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Button for setup skip not found");

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Search container not found");

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_plate"),
                "JAVA",
                "Search field not found");

        //В установленной мной версии приложения(последней) нет id контейнера у позиции результата поиска
        waitForElementsPresent(
                new ByAll(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class = 'android.view.ViewGroup']")),
                "Search results list is empty"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cancel button not found"
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results list is still present on page"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private void waitForElementPresentAndClick(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
    }

    private void waitForElementPresentAndSendKeys(By by, String value, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
    }

    private Boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private Boolean waitForElementNotPresent(By by, String error_message) {
        return waitForElementNotPresent(by, error_message, 5);
    }

    private void waitForTextElementPresent(By by, String expectedText, String error_message) {
        WebElement element = waitForElementPresent(by, error_message);
        String textOfElement = element.getText();
        assertEquals(error_message, expectedText, textOfElement);
    }

    private List<WebElement> waitForElementsPresent(ByAll byAll, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byAll));
    }

    private List<WebElement> waitForElementsPresent(ByAll byAll, String error_message) {
        return waitForElementsPresent(byAll, error_message, 5);
    }
}
