package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {

    private static final String
            BOTTOM_ELEMENT = "//*[@text = 'View page in browser']",
            BOOKMARK_ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button",
            BOOKMARK_LIST = "//*[@resource-id = 'org.wikipedia:id/item_title' and @text = '{listName}']",
            ARTICLE_TITLE = "//*[@class = 'android.view.View' and @text = '{articleTitle}']";

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
                By.id(BOOKMARK_ONBOARDING_BUTTON),
                "Onboarding button not found"
        );
    }

    public void selectListOfSavedArticles(String listName) {
         String listNameXpath = getListName(listName);
         this.waitForElementPresentAndClick(
                By.xpath(listNameXpath),
                "List with saved articles not found"
        );
    }

    public void swipeToBottom() {
         this.swipeUpToFindElement(
                 By.xpath(BOTTOM_ELEMENT),
                 "Link at bottom of the page not found",
                 20
         );
    }

    //У заголовка статьи нет никаких уникальных атрибутов для поиска(только текст), чтобы более грамотно осуществлять поиск или
    //сравнение заголовка с ожидаемым значением
    public void searchArticleTitle(String articleName) {
         String articleTitleXpath = getArticleTitle(articleName);
         this.waitForElementPresent(
                 By.xpath(articleTitleXpath),
                 "Article name not the same secondArticleName"
         );
    }

    public void checkArticleTitle(String articleName) {
        String articleTitleXpath = getArticleTitle(articleName);

        this.assertElementPresent(
                By.xpath(articleTitleXpath),
                "Expected element not found"
        );
    }
}
