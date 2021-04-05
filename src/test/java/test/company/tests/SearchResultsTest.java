package test.company.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.company.base.TestBaseSetup;
import test.company.pages.BasePage;
import test.company.pages.SearchResultsPage;

public class SearchResultsTest extends TestBaseSetup {

	@Test
	public void verifyFlightResults() {
		BasePage basePage = new BasePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

		
		basePage.verifyBasePageTitle();
		basePage.selectFrom("DEL");
		basePage.selectTo("BLR");
		basePage.DepatureDate("07042021");
		basePage.ReturnDate("24042021");
		basePage.Travellers("Adult", "2");
		basePage.Search();
		searchResultsPage.validateStopsFilterOptions();
		searchResultsPage.validateDepatureFilterOptions("DEL");
		searchResultsPage.selectFilters("1 stop");
		searchResultsPage.printAirlines(7000);
		 
	}

}
