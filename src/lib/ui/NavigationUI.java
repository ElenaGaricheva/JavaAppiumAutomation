package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            BOOKMARK_BUTTON = "id:org.wikipedia:id/article_menu_bookmark",
            MORE_OPTIONS_BUTTON = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            READING_LIST_BUTTON = "id:org.wikipedia:id/page_action_overflow_reading_lists",
            RETURN_TO_SEARCH_BUTTON = "xpath://*[@content-desc='Navigate up']",
            SKIP_SETUP_ONBOARDING = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickBookmarkButton() {
        this.waitForElementPresentAndClick(
                BOOKMARK_BUTTON,
                "Button for create bookmark not found"
        );
    }

    public void clickMoreOptionsButton() {
        this.waitForElementPresentAndClick(
                MORE_OPTIONS_BUTTON,
                "More options button not found"
        );
    }

    public void clickReadingListButton() {
        this.waitForElementPresentAndClick(
                READING_LIST_BUTTON,
                "Reading list button not found"
        );
    }

    public void goToReadingLists() {
        clickMoreOptionsButton();
        clickReadingListButton();
    }

    public void waitForReturnToSearchButtonAndAppear() {
        this.waitForElementPresent(
                RETURN_TO_SEARCH_BUTTON,
                "Button for return to search not found"
        );
    }

    public void returnToSearchPage() {
        this.waitForElementPresentAndClick(
                RETURN_TO_SEARCH_BUTTON,
                "Button for return to search not found"
        );
    }

    public void setupSkip() {
        waitForElementPresentAndClick(
                SKIP_SETUP_ONBOARDING,
                "Button for setup skip not found");
    }

}
