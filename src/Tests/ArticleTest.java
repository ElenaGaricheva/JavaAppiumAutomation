package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.BookmarkPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.BookmarkPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTest extends CoreTestCase {
    @Test
    public void testSwipeArticles() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        NavigationUI.setupSkip();

        SearchPageObject.searchAndOpenArticle("Appium");

        ArticlePageObject.swipeToBottom();
    }

    @Test
    public void testSaveArticleToMyListAndDelete(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        BookmarkPageObject BookmarkPageObject = BookmarkPageObjectFactory.get(driver);

        String listName = "Saved";
        String articleName = "Appium";

        NavigationUI.setupSkip();

        SearchPageObject.searchAndOpenArticle(articleName);

        NavigationUI.clickBookmarkButton();

        //Этот шаг актуален только при добавлении первой статьи, что мешает создать общий метод для сохранения закладки
        ArticlePageObject.skipBookmarkOnboarding();

        ArticlePageObject.selectListOfSavedArticles(listName);

        NavigationUI.goToReadingLists();

        BookmarkPageObject.skipSyncReadingList();

        BookmarkPageObject.selectListOfSavedArticles(listName);
        BookmarkPageObject.waitForSavedArticleAndAppear(articleName);
        BookmarkPageObject.deleteSavedArticle(articleName);

    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        BookmarkPageObject BookmarkPageObject = BookmarkPageObjectFactory.get(driver);

        String firstArticle = "Java (programming language)";
        String firstArticleDesc = "Object-oriented programming language";
        String secondArticle = "Appium";
        String listName = "Saved";

        NavigationUI.setupSkip();

        SearchPageObject.searchAndOpenArticle(firstArticle, firstArticleDesc);

        NavigationUI.clickBookmarkButton();

        if (Platform.getInstance().isAndroid()) {
            //Этот шаг актуален только при добавлении первой статьи, что мешает создать общий метод для сохранения закладки
            ArticlePageObject.skipBookmarkOnboarding();
            ArticlePageObject.selectListOfSavedArticles(listName);
        }

        NavigationUI.returnToSearchPage();

        SearchPageObject.clickSearchCancelButton();

        SearchPageObject.searchAndOpenArticle(secondArticle);

        NavigationUI.clickBookmarkButton();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.selectListOfSavedArticles(listName);
        }

        NavigationUI.goToReadingLists();

        BookmarkPageObject.skipSyncReadingList();

        if (Platform.getInstance().isAndroid()) {
            BookmarkPageObject.selectListOfSavedArticles(listName);
        }

        BookmarkPageObject.deleteSavedArticle(firstArticle);

        BookmarkPageObject.selectSavedArticle(secondArticle);

        ArticlePageObject.searchArticleTitle(secondArticle);
    }

    @Test
    public void testCheckArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        String articleName = "Appium";

        NavigationUI.setupSkip();

        SearchPageObject.searchAndOpenArticle(articleName);

        ArticlePageObject.checkArticleTitle(articleName);
    }
}
