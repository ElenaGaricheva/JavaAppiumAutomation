package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INPUT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_BY_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{articleName}')]";
        SEARCH_RESULT_TITLE_DESC = "xpath://XCUIElementTypeLink[contains(@name,'{articleName}\n{articleDesc}')]";
        SEARCH_CANCEL_BUTTON = "xpath://*[@name ='Cancel']";
        ITEM_SEARCH_RESULT = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell";
        ITEM_SEARCH_RESULT_TITLE = "xpath://XCUIElementTypeLink";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}