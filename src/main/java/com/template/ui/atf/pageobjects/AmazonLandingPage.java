package com.template.ui.atf.pageobjects;

import lombok.Getter;
import org.testmonkeys.maui.pageobjects.AbstractPage;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.PageAccessor;
import org.testmonkeys.maui.pageobjects.elements.Button;
import org.testmonkeys.maui.pageobjects.elements.Input;

@Getter
@PageAccessor(name = "Amazon Landing Page", url = "")
public class AmazonLandingPage extends AbstractPage {

    @ElementAccessor(elementName = "search bar", byId = "twotabsearchtextbox")
    private Input searchBar;

    @ElementAccessor(elementName = "search button", byId = "nav-search-submit-button")
    private Button searchButton;
}