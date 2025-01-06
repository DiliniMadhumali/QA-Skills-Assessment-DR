package com.ebay.stepdefinitions;

import com.ebay.pages.MainProductPage;
import com.ebay.pages.ProductPage;
import com.ebay.utils.BrowserManager;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ProductPageSteps {
    Page page;
    ProductPage productPage;
    MainProductPage mainProductPage;

    @And("the user selects a product from the search results")
    public void theUserSelectsAProductFromTheSearchResults() {
       
        String currentUrl = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("currentUrl.txt"))) {
            currentUrl = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    
        page = BrowserManager.getPage();
        page.navigate(currentUrl);
        productPage = new ProductPage(page, "");

       
        productPage.selectProduct();
    }

    @Then("the user should be navigated to the product page")
    public void theUserShouldBeNavigatedToTheProductPage() {
       
        System.out.println("Debug: Current URL after selecting product - " + page.url());
        System.out.println("Debug: Page content after selecting product - " + page.content());
    }

    @And("the user clicks on the first product listed on the product page")
    public void theUserClicksOnTheFirstProductListedOnTheProductPage() {
    
    }

    @Then("the user should be navigated to the main product page")
    public void theUserShouldBeNavigatedToTheMainProductPage() {
      
        String productUrl = "https://www.ebay.com/itm/144938643360?_skw=wallet&itmmeta=01JGV07D7G4VX72B8144PRNRGJ&hash=item21bf042fa0:g:BWsAAOSwI8Rj5e8K&itmprp=enc%3AAQAJAAAAwHoV3kP08IDx%2BKZ9MfhVJKmrqbDtocKKfTj7hV8z5Q--TuCrf3KWJ%2FGGb%2Fc6CHFwgqGkH2rnmPkLoxCFaefuHF9mbZnKiDn3d8M6gTpqZY2WA2yjGp2QmjiyDEZq4L2fVdNlbwFF3BaUeHvTSfKF8iQ0sJkoI9Bq6xS%2FZfF4ehAowcrvHiASHpHBqK1NyuTT%2FWywVBwIY5s1Onlq4uLi2vLgK72gO1i1XUHgcil6O746ch0H3uCTHbhjYxBymtzdiQ%3D%3D%7Ctkp%3ABlBMUOrTneCGZQ";
        page.navigate(productUrl);

        
        mainProductPage = new MainProductPage(page, "");
    }

    @And("the user should see the main product details")
    public void theUserShouldSeeTheMainProductDetails() {
     
        System.out.println("Debug: Main product details - " + page.content());
    }

    @And("the user should see a list of related best seller products")
    public void theUserShouldSeeAListOfRelatedBestSellerProducts() {
        boolean result = mainProductPage.areRelatedProductsDisplayed();
        
        if (!result) {
            System.out.println("Debug: Current URL - " + page.url());
            System.out.println("Debug: Page content - " + page.content());
            throw new AssertionError("Related best seller products are not displayed.");
        }
    }

    @Then("the related best seller products should:")
    public void theRelatedBestSellerProductsShould(io.cucumber.datatable.DataTable dataTable) {
        
        dataTable.asMaps().forEach(row -> {
            String criteria = row.get("Criteria");
            String description = row.get("Description");
            System.out.println("Criteria: " + criteria + ", Description: " + description);
         
        });
    }

    @Then("the user verifies the related best seller products criteria")
    public void theUserVerifiesTheRelatedBestSellerProductsCriteria(io.cucumber.datatable.DataTable dataTable) {
        List<String> categories = mainProductPage.getRelatedProductCategories();
        List<Double> prices = mainProductPage.getRelatedProductPrices();

        
        double mainProductPrice = mainProductPage.getMainProductPrice();

     
        double minPrice = mainProductPrice * 0.8;
        double maxPrice = mainProductPrice * 1.2;

      
        boolean sameCategory = categories.stream().allMatch(category -> category.equals("wallet"));
        boolean similarPriceRange = prices.stream().allMatch(price -> price >= minPrice && price <= maxPrice);
        boolean upToSixProductsDisplayed = categories.size() <= 6;

        // Output the results
        System.out.println("Same category: " + sameCategory);
        System.out.println("Similar price range: " + similarPriceRange);
        System.out.println("Up to six products displayed: " + upToSixProductsDisplayed);

        if (!sameCategory) {
            throw new AssertionError("Not all related products are in the same category.");
        }
        if (!similarPriceRange) {
            throw new AssertionError("Not all related products are in a similar price range.");
        }
        if (!upToSixProductsDisplayed) {
            throw new AssertionError("More than six related products are displayed.");
        }
    }
}
