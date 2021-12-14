package com.template.ui.atf.pageobjects;

import lombok.Getter;
import org.testmonkeys.maui.core.page.Page;
import org.testmonkeys.maui.pageobjects.AbstractPage;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.PageAccessor;
import org.testmonkeys.maui.pageobjects.elements.GenericWebElement;

@Getter
@PageAccessor(name = "Search Results Page", url = "/")
public class SearchResultsPage extends AbstractPage implements Page {

    @ElementAccessor(elementName = "results table", byId = "search")
    private GenericWebElement resultsTable;

    @ElementAccessor(elementName = "results stats", byXPath = "//*[@id=\"search\"]/span/div/span/h1/div/div[1]/div/div/span[1]")
    private GenericWebElement resultsStats;
}