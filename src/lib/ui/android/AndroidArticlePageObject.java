package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        BOTTOM_ELEMENT = "xpath://*[@text = 'View page in browser']";
        BOOKMARK_ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button";
        BOOKMARK_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title' and @text = '{listName}']";
        ARTICLE_TITLE = "xpath://*[@class = 'android.view.View' and @text = '{articleTitle}']";
    }
    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}