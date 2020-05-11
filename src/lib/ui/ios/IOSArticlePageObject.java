package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        BOTTOM_ELEMENT = "xpath://*[@text = 'View page in browser']";
        BOOKMARK_ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button";
        ARTICLE_TITLE = "xpath://XCUIElementTypeStaticText[@name='{articleTitle}']";
    }
   public IOSArticlePageObject(AppiumDriver driver) {
       super(driver);
   }
}