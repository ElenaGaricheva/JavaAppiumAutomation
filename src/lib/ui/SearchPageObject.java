package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INPUT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "org.wikipedia:id/search_plate",
            SEARCH_RESULT_BY_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text ='{articleName}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            ITEM_SEARCH_RESULT = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class = 'android.view.ViewGroup']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String articleName) {
      return SEARCH_RESULT_BY_TPL.replace("{articleName}", articleName);
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
                "Can't find search result");
    }

    public void searchAndOpenArticle(String articleName) {
        initSearchInput();
        typeSearchLine(articleName);
        String searchResultXpath = getResultSearchElement(articleName);
        this.waitForElementPresentAndClick(
                By.xpath(searchResultXpath),
                "Article" + articleName + "not found"
        );
    }
    public void waitForCancelButtonAndAppear() {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cancel button not found");
    }

    public void clickCancelButton() {
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
}
