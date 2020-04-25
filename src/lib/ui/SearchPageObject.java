package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByAll;

import static org.junit.Assert.*;
import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INPUT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "org.wikipedia:id/search_plate",
            SEARCH_RESULT_BY_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text ='{articleName}']",
            SEARCH_RESULT_TITLE_DESC = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text ='{articleName}']/.." +
                    "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{articleDesc}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            ITEM_SEARCH_RESULT = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class = 'android.view.ViewGroup']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String articleName) {
        return SEARCH_RESULT_BY_TPL.replace("{articleName}", articleName);
    };

    private static String getResultSearchElementByTitleAndDesc(String articleName, String articleDesc) {
        return SEARCH_RESULT_TITLE_DESC.replace("{articleName}", articleName)
                .replace("{articleDesc}", articleDesc);
    };
    /* TEMPLATES METHODS */


    public void initSearchInput() {

        this.waitForElementPresentAndClick(
                By.id(SEARCH_INPUT_ELEMENT),
                "Search container not found");

        waitForElementPresent(
                By.id(SEARCH_INPUT),
                "Search field not found");
    }

    public void typeSearchLine(String articleName) {
        waitForElementPresentAndSendKeys(
                By.id(SEARCH_INPUT),
                articleName,
                "Search field not found");
    }

    public void waitForSearchResult(String articleName) {
        String searchResultXpath = getResultSearchElement(articleName);

        this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Article" + articleName + "not found");
    }

    public void waitForSearchResultAndClick(String articleName) {
        String searchResultXpath = getResultSearchElement(articleName);

        this.waitForElementPresentAndClick(
                By.xpath(searchResultXpath),
                "Article" + articleName + "not found");
    }

    public void searchArticle(String articleName) {
        initSearchInput();
        typeSearchLine(articleName);
        waitForSearchResult(articleName);
    }

    public void searchArticle(String articleName, String articleDesc) {
        initSearchInput();
        typeSearchLine(articleName);
        this.waitForElementByTitleAndDesc(articleName,articleDesc);
    }

    public void searchAndOpenArticle(String articleName) {
        initSearchInput();
        typeSearchLine(articleName);
        waitForSearchResultAndClick(articleName);
    }

    public void searchAndOpenArticle(String articleName, String articleDesc) {
        initSearchInput();
        typeSearchLine(articleName);
        this.waitForElementByTitleAndDescAndClick(articleName,articleDesc);
    }
    public void waitForCancelButtonAndAppear() {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cancel button not found");
    }

    public void clickSearchCancelButton() {
        this.waitForElementPresentAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cancel button not found");
    }

    public void checkListOfSearchResult() {
       this.waitForElementsPresent(
                new ByAll(By.xpath(ITEM_SEARCH_RESULT)),
                "Search results list is empty"
        );
    }
     public void checkTitleOfEachElementsOfList(String articleName) {
        this.checkTextEachElementsOfList(
                new ByAll(By.xpath(ITEM_SEARCH_RESULT)),
                articleName,
                "Not all elements contain expected text"
        );
     }

     public void waitForElementByTitleAndDesc(String articleName, String articleDesc) {
        String searchResultXpath = getResultSearchElementByTitleAndDesc(articleName, articleDesc);
        this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Result with expected title and desc not found"
        );
     }

    public void waitForElementByTitleAndDescAndClick(String articleName, String articleDesc) {
        String searchResultXpath = getResultSearchElementByTitleAndDesc(articleName, articleDesc);
        this.waitForElementPresentAndClick(
                By.xpath(searchResultXpath),
                "Result with expected title and desc not found"
        );
    }

    public void checkNumberOfSearchResult(Integer itemsNumber) {
        List<WebElement> listElements = this.waitForElementsPresent(
                new ByAll(By.xpath(ITEM_SEARCH_RESULT)),
                "List of search result is empty");

        assertTrue("Number of list items less than 3", (listElements.size() >= itemsNumber));

    }
}
