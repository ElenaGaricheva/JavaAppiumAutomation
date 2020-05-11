package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.BookmarkPageObject;

public class AndroidBookmarkPageObject extends BookmarkPageObject {

    static {
        BOOKMARK_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title' and @text = '{listName}']";
        SAVED_ARTICLE = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '{articleName}']";
        SKIP_ONBOARDING_BUTTON = "xpath://*[@resource-id = 'android:id/button2' and @text = 'NO THANKS']";
    }

    public AndroidBookmarkPageObject(AppiumDriver driver) {
        super(driver);
    }
}