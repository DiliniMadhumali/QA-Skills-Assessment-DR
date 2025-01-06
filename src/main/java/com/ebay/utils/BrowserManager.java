package com.ebay.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManager {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);
    private static Page page;

    public static void init() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(options);
        page = browser.newPage();
    }

    public static Page getPage() {
        return page;
    }

    public static void close() {
        browser.close();
        playwright.close();
    }
}
