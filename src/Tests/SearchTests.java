package Tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchArticles() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.setupSkip();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java");
    }

    @Test
    public void testCancelSearchArticles() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        String articleName = "Java";

        NavigationUI.setupSkip();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(articleName);
        SearchPageObject.waitForSearchResult(articleName);

        //В установленной мной версии приложения(последней) нет id контейнера у позиции результата поиска
        SearchPageObject.checkListOfSearchResult();

        SearchPageObject.checkTitleOfEachElementsOfList(articleName);
    }

    @Test
    public void testSearchDifferentArticles() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.setupSkip();

        SearchPageObject.searchArticle("Appium");

        SearchPageObject.checkNumberOfSearchResult(3);

        SearchPageObject.clickSearchCancelButton();

        SearchPageObject.searchArticle("Linkin Park");

        SearchPageObject.checkNumberOfSearchResult(3);

        SearchPageObject.clickSearchCancelButton();
    }
}