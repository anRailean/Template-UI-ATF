package com.template.ui.atf.stepdef;

import com.template.ui.atf.Context;
import com.template.ui.atf.pageobjects.AmazonLandingPage;
import com.template.ui.atf.pageobjects.SearchResultsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.testmonkeys.maui.core.page.Page;
import org.testmonkeys.maui.pageobjects.elements.Button;
import org.testmonkeys.maui.pageobjects.elements.Input;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchSteps {

    @Autowired
    private Context context;

    private final String DEFAULT_DRIVER = "Default";

    private final String SEARCH_RESULTS_PAGE = "Search Results Page";

    @Given("user navigates to {string}")
    public void navigateToPage(String pageName) {
        Page page = context.getUiEnvironment(DEFAULT_DRIVER).getPageFactory().createPage(pageName);
        page.open();
        context.put(pageName, page);
        context.takeScreenshot(pageName, DEFAULT_DRIVER);
    }

    @When("user searches for {string} in search bar")
    public void searchFor(String searchKey) {
        String AMAZON_LANDING_PAGE = "Amazon Landing Page";
        AmazonLandingPage landingPage = context.get(AMAZON_LANDING_PAGE);

        Input searchBar = landingPage.getSearchBar();
        searchBar.type(searchKey);

        Button searchButton = landingPage.getSearchButton();
        searchButton.click();

        context.takeScreenshot("Search Results", DEFAULT_DRIVER);
    }

    @Then("Search Results Page contains at least {int} result")
    public void containsAtLeast(int resultsNumber) {
        SearchResultsPage searchResultsPage = context.getUiEnvironment(DEFAULT_DRIVER)
                .getPageFactory()
                .createPage(SearchResultsPage.class);
        context.put(SEARCH_RESULTS_PAGE, searchResultsPage);

        assertThat(Integer.parseInt(
                searchResultsPage.getResultsStats()
                        .getText().replaceAll(
                        "[^0-9]", "")) >= resultsNumber)
                .isTrue();

        context.takeScreenshot(SEARCH_RESULTS_PAGE, DEFAULT_DRIVER);
    }

    @Then("Search Results Page contains results for {string}")
    public void pageContains(String searchKey) {
        SearchResultsPage searchResultsPage = context.get(SEARCH_RESULTS_PAGE);

        assertThat(
                searchResultsPage.getResultsTable()
                        .getText()
                        .contains(searchKey)).isTrue();

        context.takeScreenshot(searchKey, DEFAULT_DRIVER);
    }
}