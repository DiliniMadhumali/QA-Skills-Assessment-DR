package com.ebay.pages;

import com.microsoft.playwright.Page;

public class ProductPage extends BasePage {
    private String productXPath = "(//ul[@class='srp-results srp-grid clearfix']//li[@class='s-item s-item__pl-on-bottom'])[1]//img";

    public ProductPage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public void selectProduct() {
        page.waitForSelector(productXPath);
        page.click(productXPath);
    }
}
