package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INPUT_ELEMENT = "id:org.wikipedia:id/search_container";
                SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
                SEARCH_RESULT_BY_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text ='{articleName}']";
                SEARCH_RESULT_TITLE_DESC = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text ='{articleName}']/.." +
                        "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{articleDesc}']";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                ITEM_SEARCH_RESULT = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class = 'android.view.ViewGroup']";
                ITEM_SEARCH_RESULT_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}