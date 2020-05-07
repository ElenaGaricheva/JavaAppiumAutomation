package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByAll;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INPUT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_TPL,
            SEARCH_RESULT_TITLE_DESC ,
            SEARCH_CANCEL_BUTTON,
            ITEM_SEARCH_RESULT,
            ITEM_SEARCH_RESULT_TITLE;

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
                SEARCH_INPUT_ELEMENT,
                "Search container not found");

        waitForElementPresent(
                SEARCH_INPUT,
                "Search field not found");
    }

    public void typeSearchLine(String articleName) {
        waitForElementPresentAndSendKeys(
                SEARCH_INPUT,
                articleName,
                "Search field not found");
    }

    public void waitForSearchResult(String articleName) {
        String searchResultXpath = getResultSearchElement(articleName);

        this.waitForElementPresent(
                searchResultXpath,
                "Article" + articleName + " not found");
    }

    public void waitForSearchResultAndClick(String articleName) {
        String searchResultXpath = getResultSearchElement(articleName);

        this.waitForElementPresentAndClick(
                searchResultXpath,
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
                SEARCH_CANCEL_BUTTON,
                "Cancel button not found");
    }

    public void clickSearchCancelButton() {
        this.waitForElementPresentAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cancel button not found");
    }

    public void checkListOfSearchResult() {
       this.waitForElementsPresent(
                ITEM_SEARCH_RESULT,
                "Search results list is empty"
        );
    }
     public void checkTitleOfEachElementsOfList(String articleName) {
        this.checkTextEachElementsOfList(
                ITEM_SEARCH_RESULT,
                ITEM_SEARCH_RESULT_TITLE,
                articleName,
                "Not all elements contain expected text"
        );
     }

     public void waitForElementByTitleAndDesc(String articleName, String articleDesc) {
        String searchResultXpath = getResultSearchElementByTitleAndDesc(articleName, articleDesc);
        this.waitForElementPresent(
                searchResultXpath,
                "Result with expected title and desc not found"
        );
     }

    public void waitForElementByTitleAndDescAndClick(String articleName, String articleDesc) {
        String searchResultXpath = getResultSearchElementByTitleAndDesc(articleName, articleDesc);
        this.waitForElementPresentAndClick(
                searchResultXpath,
                "Result with expected title and desc not found"
        );
    }

    public void checkNumberOfSearchResult(Integer itemsNumber) {
        List<WebElement> listElements = this.waitForElementsPresent(
                ITEM_SEARCH_RESULT,
                "List of search result is empty");
        assertTrue("Number of list items less than" + itemsNumber, (listElements.size() >= itemsNumber));

    }
}
