package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.*;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public void waitForElementPresentAndClick(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        element.click();
    }

    public void waitForElementPresentAndSendKeys(By by, String value, String error_message) {
        WebElement element = waitForElementPresent(by, error_message);
        element.clear();
        element.sendKeys(value);
    }

    public Boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public Boolean waitForElementNotPresent(By by, String error_message) {
        return waitForElementNotPresent(by, error_message, 5);
    }

    public void waitForTextElementPresent(By by, String expectedText, String error_message) {
        WebElement element = waitForElementPresent(by, error_message);
        String textOfElement = element.getText();
        assertEquals(error_message, expectedText, textOfElement);
    }

    public List<WebElement> waitForElementsPresent(ByAll byAll, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byAll));
    }

    public List<WebElement> waitForElementsPresent(ByAll byAll, String error_message) {
        return waitForElementsPresent(byAll, error_message, 5);
    }

    public void checkTextEachElementsOfList(ByAll byAll, String expectedText, String error_message) {
        List<WebElement> listElements = waitForElementsPresent(byAll, error_message);
        listElements.stream()
                .map(el -> el.findElement(By.id("org.wikipedia:id/page_list_item_title")).getText())
                .forEach(txt -> assertTrue(error_message, txt.contains(expectedText)));
    }

    public void assertElementPresent(By by, String error_message) {
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
                .press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {

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

    public void swipeElementToLeft(By by, String error_message) {
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
