package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.BookmarkPageObject;
import lib.ui.android.AndroidBookmarkPageObject;
import lib.ui.ios.IOSBookmarkPageObject;

public class BookmarkPageObjectFactory {
    public static BookmarkPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidBookmarkPageObject(driver);
        }
        else {
            return new IOSBookmarkPageObject(driver);
        }
    }
}