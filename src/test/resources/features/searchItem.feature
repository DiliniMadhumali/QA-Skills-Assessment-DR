@feature
Feature: Shopping for a wallet and verifying related best seller products

  Scenario: Verify related best seller products after selecting a wallet
    Given the user is on the eBay HomePage
    When the user searches for "wallet"
    And the user selects a product from the search results
    Then the user should be navigated to the product page
    And the user clicks on the first product listed on the product page
    Then the user should be navigated to the main product page
    And the user should see the main product details
    And the user should see a list of related best seller products
    Then the related best seller products should:
      | Criteria                     | Description                                                    |
      | Same category                | Related products should be wallets                             |
      | Similar price range          | Products should be in the same price range as the main product |
      | Up to six products displayed | Display a maximum of six related best seller products          |
