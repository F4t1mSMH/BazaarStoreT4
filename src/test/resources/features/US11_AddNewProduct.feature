@Manager
Feature: Product Creation
  @Smoke @Regression
  Scenario: Create API product and verify UI functionality
    Given a valid product payload is prepared
    When a POST request is sent to create the product via API
    Then the product is created successfully and product ID is stored

    #Given the store Manager logged in on the dashboard page
    And the Manager click on product page

    Then the product can be retrieved successfully via API using its ID

  @Regression @Negative
  Scenario: verify error message when Add a new product and leave empty name
    And the Manager click on product page
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave name empty
    And the Store Manager submits the product form
    Then an error message "The name field is required." should be displayed


  @Regression @Negative
  Scenario: verify error message when Add a new product and leave empty price
    And the Manager click on product page
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave price empty
    And the Store Manager submits the product form
    Then an error message "The price field is required." should be displayed

  @Regression @Negative
  Scenario: verify error message when Add a new product and leave empty stock
    And the Manager click on product page
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave stoke empty
    And the Store Manager submits the product form
    Then an error message "The stock field is required." should be displayed

  @Regression @Negative
  Scenario: verify error message when Add a new product and leave empty sku
    And the Manager click on product page
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave sku empty
    And the Store Manager submits the product form
    Then an error message "The sku field is required." should be displayed

  @Regression @Negative
  Scenario: verify error message when Add a new product and used duplicate sku
    And the Manager click on product page
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and enter used sku
    And the Store Manager submits the product form
    Then an error message "The sku has already been taken." should be displayed