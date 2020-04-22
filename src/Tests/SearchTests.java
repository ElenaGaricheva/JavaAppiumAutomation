package Tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchArticles() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);

        NavigationUI.setupSkip();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java");
    }

    @Test
    public void testCancelSearchArticles() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);

        String articleName = "Java";

        NavigationUI.setupSkip();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(articleName);
        SearchPageObject.waitForSearchResult(articleName);

        //В установленной мной версии приложения(последней) нет id контейнера у позиции результата поиска
        SearchPageObject.checkListOfSearchResult();

        SearchPageObject.checkTitleOfEachElementsOfList(articleName);
        SearchPageObject.clickCancelButton();
    }
}
