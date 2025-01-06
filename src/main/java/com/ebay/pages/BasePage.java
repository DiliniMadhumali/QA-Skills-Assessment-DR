package com.ebay.pages;

import com.microsoft.playwright.Page;

public class BasePage {
    protected Page page;
    protected String baseUrl;

    public BasePage(Page page, String baseUrl) {
        this.page = page;
        this.baseUrl = baseUrl;
    }

    public void navigate(String path) {
        page.navigate(baseUrl + path);
    }
}
