package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public void waitForElementPresentAndClick(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 10);
        element.click();
    }

    public void waitForElementPresentAndClick(String locator, String error_message, long timeOutSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutSeconds);
        element.click();
    }

    public void waitForElementPresentAndSendKeys(String locator, String value, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message);
        element.click();
        element.sendKeys(value);
    }

    public Boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public Boolean waitForElementNotPresent(String locator, String error_message) {
        return waitForElementNotPresent(locator, error_message, 5);
    }

    public void waitForTextElementPresent(String locator, String expectedText, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message);
        String textOfElement = element.getText();
        assertEquals(error_message, expectedText, textOfElement);
    }

    public List<WebElement> waitForElementsPresent(String locator, String error_message, long timeOutInSeconds) {
        ByAll byAll = new ByAll(getLocatorByString(locator));
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byAll));
    }

    public List<WebElement> waitForElementsPresent(String locator, String error_message) {
        return waitForElementsPresent(locator, error_message, 5);
    }

    public void checkTextEachElementsOfList(String locator, String locator_list_item, String expectedText, String error_message) {
        List<WebElement> listElements = waitForElementsPresent(locator, error_message);
        By list_item_title = this.getLocatorByString(locator_list_item);
        listElements.stream()
                .map(el -> el.findElement(list_item_title).getText())
                .forEach(txt -> assertTrue(error_message, txt.contains(expectedText)));
    }

    public void assertElementPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        try {
            driver.findElement(by);
        }
        catch (NoSuchElementException e) {
            throw new AssertionError(error_message);
        }
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x,start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swipe = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swipe > max_swipes) {
                waitForElementPresent(locator, error_message);
                return;
            }
            swipeUpQuick();
            ++already_swipe;
        }
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message);
        int left_x = (int) (element.getSize().getWidth() * 0.30);
        int right_x = (int) (element.getSize().getWidth() * 0.70);
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        new TouchAction(driver)
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();
    }

    public void clickElementToTheRigthUpperCorner(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator+ "/..", error_message);
        int point_to_click_x = (int) (element.getSize().getWidth() * 0.90);
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int point_to_click_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        new TouchAction(driver)
                .tap(PointOption.point(point_to_click_x, point_to_click_y))
                .perform();
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        }
        else if (by_type.equals("id")) {
            return By.id(locator);
        }
        else throw new IllegalArgumentException("Cannot get type of locator. Locator = " + locator_with_type);
    }
}
