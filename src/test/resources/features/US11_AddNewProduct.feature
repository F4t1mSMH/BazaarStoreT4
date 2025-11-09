Feature: Product Creation
  Background: user goes to product page

  @add
  Scenario: Successful Add a new product as a store manager
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then the new product should appear in the product list
    And a success message "Product added successfully" should be displayed

  @Negative
  Scenario: Add a new product and leave empty name
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The name field is required" should be displayed


  @Negative
  Scenario: Add a new product and leave empty price
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The price field is required" should be displayed

  @Negative
  Scenario: Add a new product and leave empty stock
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The stock field is required" should be displayed

  @Negative
  Scenario: Add a new product and leave empty sku
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The sku field is required" should be displayed
