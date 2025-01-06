package com.ebay.pages;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {
    private String searchBoxXPath = "//input[@id='gh-ac']";
    private String searchButtonXPath = "//input[@id='gh-btn']";

    public HomePage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public void searchProduct(String product) {
        page.fill(searchBoxXPath, product);
        page.click(searchButtonXPath);
    }

    public String getCurrentUrl() {
        return page.url();
    }
}
