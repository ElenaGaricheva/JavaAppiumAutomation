package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            BOOKMARK_BUTTON,
            SAVED_ARTICLES_LIST,
            MORE_OPTIONS_BUTTON,
            READING_LIST_BUTTON,
            RETURN_TO_SEARCH_BUTTON,
            RETURN_TO_EXPLORE_BUTTON,
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

    public void goToMainPage() {
        this.waitForElementPresentAndClick(
                RETURN_TO_EXPLORE_BUTTON,
                "Return to explore button not found"
        );
    }

    public void goToSavedArticlesList() {
        this.waitForElementPresentAndClick(
                SAVED_ARTICLES_LIST,
                "Saved articles list button not found"
        );
    }

    public void clickReadingListButton() {
        this.waitForElementPresentAndClick(
                READING_LIST_BUTTON,
                "Reading list button not found"
        );
    }

    public void goToReadingLists() {
        if (Platform.getInstance().isAndroid()) {
            clickMoreOptionsButton();
            clickReadingListButton();
        }
        else if (Platform.getInstance().isIOS()) {
            goToMainPage();
            goToSavedArticlesList();
        }
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
