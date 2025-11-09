@Manger
Feature: Product Creation
  Background:
    Given the store manger logged in on the dashboard page
    And the manger click on product page

  @addProduct
  Scenario: Successful Add a new product as a store manager
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    And the new product should appear in the product list
    Then a success message "Product created successfully!" should be displayed


  @Negative @NegativeAddOnly
  Scenario: Add a new product and leave empty name
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave name empty
    And the Store Manager submits the product form
    Then an error message "The name field is required" should be displayed


  @Negative @NegativeAddOnly
  Scenario: Add a new product and leave empty price
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave price empty
    And the Store Manager submits the product form
    Then an error message "The price field is required" should be displayed

  @Negative @NegativeAddOnly
  Scenario: Add a new product and leave empty stock
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave stoke empty
    And the Store Manager submits the product form
    Then an error message "The stock field is required" should be displayed

  @Negative @NegativeAddOnly
  Scenario: Add a new product and leave empty sku
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave sku empty
    And the Store Manager submits the product form
    Then an error message "The sku field is required" should be displayed

  @Negative @NegativeAddOnly
  Scenario: Add a new product and used empty sku
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and enter used sku
    And the Store Manager submits the product form
    Then an error message "The sku has already been taken." should be displayed
