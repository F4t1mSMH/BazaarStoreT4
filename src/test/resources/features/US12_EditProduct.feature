@Manager @Edit
Feature: Product Edit
  Background:
    Given a valid product payload is prepared
    When a POST request is sent to create the product via API
    Then the product is created successfully and product ID is stored

    And the Manager click on product page

  @Smoke @Regression
  Scenario: Successful API edit and verification
    Given edited product details are generated
    When a PUT request is sent to update the product via API
    Then the product details should be updated successfully via API
    And the product can be retrieved successfully via API using its ID


  @Regression @Edit
  Scenario: Edit product and delete name
    When the Store Manager clicks the Edit button for the created product
    And the Store Manager edit product details delete name
    And the Store Manager submits the product form
    Then an error message "The name field is required" should be displayed


  @Regression @Edit
  Scenario: Edit product and delete price
    When the Store Manager clicks the Edit button for the created product
    And the Store Manager edit product details and delete price
    And the Store Manager submits the product form
    Then an error message "The price field is required" should be displayed


  @Regression @Edit
  Scenario: Edit product and delete stock
    When the Store Manager clicks the Edit button for the created product
    And the Store Manager edit product details and delete stock
    And the Store Manager submits the product form
    Then an error message "The stock field is required" should be displayed


  @Regression @Edit
  Scenario: Edit product and delete sku
    When the Store Manager clicks the Edit button for the created product
    And the Store Manager edit product details and delete sku
    And the Store Manager submits the product form
    Then an error message "The sku field is required" should be displayed