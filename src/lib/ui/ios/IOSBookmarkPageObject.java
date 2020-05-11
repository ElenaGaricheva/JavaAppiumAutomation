package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.BookmarkPageObject;

public class IOSBookmarkPageObject extends BookmarkPageObject {

    static {
        BOOKMARK_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title' and @text = '{listName}']";
        SAVED_ARTICLE = "xpath://XCUIElementTypeLink[contains(@name,'{articleName}')]";
        SKIP_ONBOARDING_BUTTON = "id:Close";
    }
    public  IOSBookmarkPageObject(AppiumDriver driver) {
        super(driver);
    }
}