package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        BOOKMARK_BUTTON = "id:Save for later";
        SAVED_ARTICLES_LIST = "xpath://XCUIElementTypeButton[@name='Saved']";
        MORE_OPTIONS_BUTTON = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu";
        READING_LIST_BUTTON = "id:org.wikipedia:id/page_action_overflow_reading_lists";
        RETURN_TO_SEARCH_BUTTON = "id:Back";
        RETURN_TO_EXPLORE_BUTTON = "xpath: //XCUIElementTypeButton[@name='Wikipedia, return to Explore']";
        SKIP_SETUP_ONBOARDING = "xpath://*[@name='Skip']";
    }

    public  IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}