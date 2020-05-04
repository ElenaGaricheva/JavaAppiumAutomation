package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {

    private static final String
            BOTTOM_ELEMENT = "xpath://*[@text = 'View page in browser']",
            BOOKMARK_ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button",
            BOOKMARK_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title' and @text = '{listName}']",
            ARTICLE_TITLE = "xpath://*[@class = 'android.view.View' and @text = '{articleTitle}']";

     public ArticlePageObject(AppiumDriver driver) {
         super(driver);
     }

    /* TEMPLATES METHODS */
    private static String getListName(String listName) {
        return BOOKMARK_LIST.replace("{listName}", listName);
    };

    private static String getArticleTitle(String articleTitle) {
        return ARTICLE_TITLE.replace("{articleTitle}", articleTitle);
    };
    /* TEMPLATES METHODS */



    public void skipBookmarkOnboarding() {
        this.waitForElementPresentAndClick(
                BOOKMARK_ONBOARDING_BUTTON,
                "Onboarding button not found"
        );
    }

    public void selectListOfSavedArticles(String listName) {
         String listNameXpath = getListName(listName);
         this.waitForElementPresentAndClick(
                listNameXpath,
                "List with saved articles not found"
        );
    }

    public void swipeToBottom() {
         this.swipeUpToFindElement(
                 BOTTOM_ELEMENT,
                 "Link at bottom of the page not found",
                 20
         );
    }

    //У заголовка статьи нет никаких уникальных атрибутов для поиска(только текст), чтобы более грамотно осуществлять поиск или
    //сравнение заголовка с ожидаемым значением
    public void searchArticleTitle(String articleName) {
         String articleTitleXpath = getArticleTitle(articleName);
         this.waitForElementPresent(
                 articleTitleXpath,
                 "Article name not the same secondArticleName"
         );
    }

    public void checkArticleTitle(String articleName) {
        String articleTitleXpath = getArticleTitle(articleName);

        this.assertElementPresent(
                articleTitleXpath,
                "Expected element not found"
        );
    }
}
