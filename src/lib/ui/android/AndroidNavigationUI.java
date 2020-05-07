package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        BOOKMARK_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
        MORE_OPTIONS_BUTTON = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu";
        READING_LIST_BUTTON = "id:org.wikipedia:id/page_action_overflow_reading_lists";
        RETURN_TO_SEARCH_BUTTON = "xpath://*[@content-desc='Navigate up']";
        SKIP_SETUP_ONBOARDING = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}