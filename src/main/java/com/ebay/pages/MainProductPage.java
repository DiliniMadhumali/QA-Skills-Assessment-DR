
package com.ebay.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.stream.Collectors;

public class MainProductPage extends BasePage {
    private String relatedProductsXPath = "//div[@class='kOaK Li1U']";
    private String categoryXPath = ".//div[contains(@class, 's-item__info')]//span[contains(@class, 'SECONDARY_INFO')]";
    private String priceXPath = ".//span[contains(@class, 's-item__price')]";

    public MainProductPage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public boolean areRelatedProductsDisplayed() {
        try {
          
            page.evaluate("window.scrollBy(0, document.body.scrollHeight)");

      
            page.waitForTimeout(5000);

         
            page.waitForSelector(relatedProductsXPath, new Page.WaitForSelectorOptions().setTimeout(30000)); // Wait up to 30 seconds

          
            boolean isVisible = page.isVisible(relatedProductsXPath);

            // Check if related products are present
            List<ElementHandle> relatedProducts = page.querySelectorAll(relatedProductsXPath);
            boolean isPresent = !relatedProducts.isEmpty();

            // Debug information
            if (isPresent && isVisible) {
                System.out.println("Related best seller products are present and visible.");
            } else {
                System.out.println("Related best seller products are NOT present or NOT visible.");
              
                System.out.println("Debug: Full Page content - " + page.content());
               
                List<ElementHandle> containers = page.querySelectorAll("//div[@class='kOaK Li1U']");
                for (ElementHandle container : containers) {
                    System.out.println("Debug: Container HTML - " + container.innerHTML());
                }
            }

            return isPresent && isVisible;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getRelatedProductCategories() {
       
        page.waitForSelector(relatedProductsXPath);
        
        // Find all related product elements
        List<ElementHandle> relatedProducts = page.querySelectorAll(relatedProductsXPath);
        
        // Extract categories for each related product
        List<String> categories = relatedProducts.stream()
            .map(product -> product.querySelector(categoryXPath))
            .filter(categoryElement -> categoryElement != null)
            .map(categoryElement -> categoryElement.innerText())
            .collect(Collectors.toList());
        
        return categories;
    }

    public List<Double> getRelatedProductPrices() {
      
        page.waitForSelector(relatedProductsXPath);
        
   
        List<ElementHandle> relatedProducts = page.querySelectorAll(relatedProductsXPath);
        
        // Extract prices for each related product
        List<Double> prices = relatedProducts.stream()
            .map(product -> product.querySelector(priceXPath))
            .filter(priceElement -> priceElement != null)
            .map(priceElement -> {
                String priceText = priceElement.innerText().replaceAll("[^\\d.]", ""); // Remove non-numeric characters
                return Double.parseDouble(priceText);
            })
            .collect(Collectors.toList());
        
        return prices;
    }

    public double getMainProductPrice() {
        // Assume main product price is fetched from the main product details
        String mainProductPriceXPath = "//span[contains(@class, 'main-product-price')]";
        ElementHandle priceElement = page.querySelector(mainProductPriceXPath);
        String priceText = priceElement.innerText().replaceAll("[^\\d.]", ""); // Remove non-numeric characters
        return Double.parseDouble(priceText);
    }
}
