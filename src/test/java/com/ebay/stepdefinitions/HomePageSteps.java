package com.ebay.stepdefinitions;

import com.ebay.pages.HomePage;
import com.ebay.utils.BrowserManager;
import com.ebay.utils.ConfigReader;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;

import java.io.FileWriter;
import java.io.IOException;

public class HomePageSteps {
    ConfigReader configReader;
    String baseUrl;
    String searchTerm;
    Page page;
    HomePage homePage;

    public HomePageSteps() {
        configReader = new ConfigReader();
        baseUrl = configReader.getProperty("baseUrl");
        searchTerm = configReader.getProperty("searchTerm");
    }

    @Given("the user is on the eBay HomePage")
    public void theUserIsOnTheHomePage() {
        BrowserManager.init();
        page = BrowserManager.getPage();
        homePage = new HomePage(page, baseUrl);
        homePage.navigate("");
    }

    @When("the user searches for {string}")
    public void theUserSearchesForAProduct(String product) {
        homePage.searchProduct(product);
        try (FileWriter writer = new FileWriter("currentUrl.txt")) {
            writer.write(homePage.getCurrentUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
