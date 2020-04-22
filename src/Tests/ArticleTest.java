package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.BookmarkPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTest extends CoreTestCase {
    @Test
    public void testSwipeArticles() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        NavigationUI.setupSkip();

        SearchPageObject.searchAndOpenArticle("Appium");

        ArticlePageObject.swipeToBottom();
    }

    @Test
    public void testSaveArticleToMyListAndDelete(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        BookmarkPageObject BookmarkPageObject = new BookmarkPageObject(driver);

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

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        BookmarkPageObject BookmarkPageObject = new BookmarkPageObject(driver);

        String firstArticle = "Java (programming language)";
        String secondArticle = "Appium";
        String listName = "Saved";

        NavigationUI.setupSkip();

        SearchPageObject.searchAndOpenArticle(firstArticle);

        NavigationUI.clickBookmarkButton();

        //Этот шаг актуален только при добавлении первой статьи, что мешает создать общий метод для сохранения закладки
        ArticlePageObject.skipBookmarkOnboarding();

        ArticlePageObject.selectListOfSavedArticles(listName);

        NavigationUI.returnToSearchPage();

        SearchPageObject.clickCancelButton();

        SearchPageObject.searchAndOpenArticle(secondArticle);

        NavigationUI.clickBookmarkButton();

        ArticlePageObject.selectListOfSavedArticles(listName);

        NavigationUI.goToReadingLists();

        BookmarkPageObject.skipSyncReadingList();

        BookmarkPageObject.selectListOfSavedArticles(listName);

        BookmarkPageObject.deleteSavedArticle(firstArticle);

        BookmarkPageObject.selectSavedArticle(secondArticle);

        ArticlePageObject.searchArticleTitle(secondArticle);
    }

    @Test
    public void testCheckArticleTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);

        String articleName = "Appium";

        NavigationUI.setupSkip();

        SearchPageObject.searchAndOpenArticle(articleName);

        ArticlePageObject.checkArticleTitle(articleName);
    }
}
