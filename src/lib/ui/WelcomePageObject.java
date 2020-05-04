package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String GO_TO_WELCOME_PAGE = "xpath://*[@name = 'Next']";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void skipOnboardingPage() {
        this.waitForElementPresentAndClick(
                GO_TO_WELCOME_PAGE,
                "Button to go to welcome page not found"
        );
    }
}
