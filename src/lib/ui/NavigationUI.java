package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            BOOKMARK_BUTTON,
            MORE_OPTIONS_BUTTON,
            READING_LIST_BUTTON,
            RETURN_TO_SEARCH_BUTTON,
            SKIP_SETUP_ONBOARDING;

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
