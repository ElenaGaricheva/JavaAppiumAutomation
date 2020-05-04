package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class BookmarkPageObject extends MainPageObject {

    private static final String
            BOOKMARK_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title' and @text = '{listName}']",
            SAVED_ARTICLE = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '{articleName}']",
            SKIP_ONBOARDING_BUTTON = "xpath://*[@resource-id = 'android:id/button2' and @text = 'NO THANKS']";

    public BookmarkPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getListName(String listName) {
        return BOOKMARK_LIST.replace("{listName}", listName);
    };
    private static String getArticleName(String articleName) {
        return SAVED_ARTICLE.replace("{articleName}", articleName);
    };
    /* TEMPLATES METHODS */

    public void selectListOfSavedArticles(String listName) {
        String listNameXpath = getListName(listName);
        this.waitForElementPresentAndClick(
                listNameXpath,
                "Test list not found"
        );
    }

    public void waitForSavedArticleAndAppear(String articleName) {
        String articleNameXpath = getArticleName(articleName);
        this.waitForElementPresent(
                articleNameXpath,
                "Saved article not found"
        );
    }

    public void selectSavedArticle(String articleName) {
        String articleNameXpath = getArticleName(articleName);
        this.waitForElementPresentAndClick(
                articleNameXpath,
                "Saved article not found"
        );
    }

    public void waitForSavedArticleAndDisappear(String articleName) {
        String articleNameXpath = getListName(articleName);

        waitForElementNotPresent(
                articleNameXpath,
                "Saved article is still present on page"
        );
    }

    public void deleteSavedArticle(String articleName) {
        String articleNameXpath = getArticleName(articleName);

        this.swipeElementToLeft(
                articleNameXpath,
                "Saved article not found"
        );
        waitForSavedArticleAndDisappear(articleName);
    }

    public void skipSyncReadingList() {
        this.waitForElementPresentAndClick(
                SKIP_ONBOARDING_BUTTON,
                "Button for dismiss sync reading list not found"
        );
    }
}
