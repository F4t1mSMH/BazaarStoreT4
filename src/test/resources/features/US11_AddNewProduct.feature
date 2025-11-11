@Manger
Feature: Product Creation
  @addProduct_API
  Scenario: Create API product and verify UI functionality
    # API SETUP
    Given a valid product payload is prepared
    When a POST request is sent to create the product via API
    Then the product is created successfully and product ID is stored

    # UI VERIFICATION
    Given the store manger logged in on the dashboard page
    And the manger click on product page

    Then the product can be retrieved successfully via API using its ID

  @NegativeAddOnly
  Scenario: verify error message when Add a new product and leave empty name
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave name empty
    And the Store Manager submits the product form
    Then an error message "The name field is required." should be displayed


  @NegativeAddOnly
  Scenario:verify error message when Add a new product and leave empty price
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave price empty
    And the Store Manager submits the product form
    Then an error message "The price field is required." should be displayed

  @NegativeAddOnly
  Scenario:verify error message when Add a new product and leave empty stock
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave stoke empty
    And the Store Manager submits the product form
    Then an error message "The stock field is required." should be displayed

  @NegativeAddOnly
  Scenario:verify error message when Add a new product and leave empty sku
    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and leave sku empty
    And the Store Manager submits the product form
    Then an error message "The sku field is required." should be displayed

  @NegativeAddOnly @Hybrid
  Scenario: verify error message when Add a new product and used duplicate sku
    Given a product exists in the system with the duplicate SKU

    When the Store Manager clicks on the "Add Product" button
    And the Store Manager enters product details and enter used sku
    And the Store Manager submits the product form
    Then an error message "The sku has already been taken." should be displayed