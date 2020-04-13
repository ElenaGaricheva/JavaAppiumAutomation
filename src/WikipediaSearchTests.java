import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

        searchArticle("Java");

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

        searchArticle("Java");

        //В установленной мной версии приложения(последней) нет id контейнера у позиции результата поиска
        waitForElementsPresent(
                new ByAll(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class = 'android.view.ViewGroup']")),
                "Search results list is empty"
        );

        checkTextEachElementsOfList(
                new ByAll(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class = 'android.view.ViewGroup']")),
                "Java",
                "Not all elements contain expected text"
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

    @Test
    public void swipeArticlesTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Button for setup skip not found");

        searchAndOpenArticle("Appium");

        swipeUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Link at bottom of the page not found",
                20
        );
    }

    @Test
    public void saveArticleToMyListAndDelete() throws InterruptedException {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Button for setup skip not found");

        searchAndOpenArticle("Appium");

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Button for create bookmark not found"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Onboarding button not found"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title' and @text = 'Saved']"),
                "List with saved articles not found"
        );


        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu"),
                "More options button not found"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/page_action_overflow_reading_lists"),
                "Reading list button not found"
        );


        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'android:id/button2' and @text = 'NO THANKS']"),
                "Button for dismiss sync reading list not found"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title' and @text = 'Saved']"),
                "Test list not found"
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = 'Appium']"),
                "Saved article not found"
        );

        swipeElementToLeft(
                By.xpath("//*[@text = 'Appium']"),
                "Saved article not found"
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = 'Appium']"),
                "Saved article is still present on page"
                );

    }

    @Test
    public void saveTwoArticlesAndDeleteOne() {
        String firstArticle = "Java (programming language)";
        String secondArticle = "Appium";

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Button for setup skip not found");

        searchAndOpenArticle(firstArticle);

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Button for create bookmark not found"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Onboarding button not found"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title' and @text = 'Saved']"),
                "List with saved articles not found"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@content-desc = 'Navigate up']"),
                "Button for return to search not found"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cancel button not found"
        );

        searchAndOpenArticle(secondArticle);

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Button for create bookmark not found"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title' and @text = 'Saved']"),
                "List with saved articles not found"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu"),
                "More options button not found"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/page_action_overflow_reading_lists"),
                "Reading list button not found"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'android:id/button2' and @text = 'NO THANKS']"),
                "Button for dismiss sync reading list not found"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title' and @text = 'Saved']"),
                "Test list not found"
        );

        swipeElementToLeft(
                By.xpath("//*[@text = '" + firstArticle +"']"),
                "Saved article not found"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text = '" + firstArticle +"']"),
                "Article is still present on page"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@text = '" + secondArticle +"']"),
                "Article not found"
                );

        waitForElementPresent(
                By.xpath("//*[@class = 'android.view.View' and @text = '" + secondArticle + "']"),
                "Article name not the same secondArticleName"
        );
    }

    private void searchArticle(String articleName) {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Search container not found");

        waitForElementPresentAndSendKeys(
                By.id("org.wikipedia:id/search_plate"),
                articleName,
                "Search field not found");
    }

    private void searchAndOpenArticle(String articleName) {

        searchArticle(articleName);

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text ='" + articleName + "']"),
                "Article not found"
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
        WebElement element = waitForElementPresent(by, error_message, 10);
        element.click();
    }

    private void waitForElementPresentAndSendKeys(By by, String value, String error_message) {
        WebElement element = waitForElementPresent(by, error_message);
        element.clear();
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

    private void checkTextEachElementsOfList(ByAll byAll, String expectedText, String error_message) {
        List<WebElement> listElements = waitForElementsPresent(byAll, error_message);
        listElements.stream()
                .map(el -> el.findElement(By.id("org.wikipedia:id/page_list_item_title")).getText())
                .forEach(txt -> assertTrue(error_message, txt.contains(expectedText)));
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {

        int already_swipe = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swipe > max_swipes) {
                waitForElementPresent(by, error_message);
                return;
            }
            swipeUpQuick();
            ++already_swipe;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 15);
        int left_x = (int) (element.getSize().getWidth() * 0.30);
        int right_x = (int) (element.getSize().getWidth() * 0.70);
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        new TouchAction(driver)
                .press(right_x, middle_y)
                .waitAction(500)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}
