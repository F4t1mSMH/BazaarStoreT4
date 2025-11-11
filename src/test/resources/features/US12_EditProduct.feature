@Manger
Feature: Product Edit
  Background:
    Given the store manger logged in on the dashboard page
    And the manger click on product page

  @smoke @regression
  Scenario: Successful API edit and verification
    Given a valid product payload is prepared
    When a POST request is sent to create the product via API
    Then the product is created successfully and product ID is stored

    Given edited product details are generated
    When a PUT request is sent to update the product via API

    Then the product details should be updated successfully via API

    And the product can be retrieved successfully via API using its ID

  @regression
  Scenario: Edit product and delete name
    When the Store Manager clicks on the "Edit Product" button
    And the Store Manager edit product details delete name
    And the Store Manager submits the product form
    Then an error message "The name field is required" should be displayed


  @regression
  Scenario: Edit product and delete price

    When the Store Manager clicks on the "Edit Product" button
    And the Store Manager edit product details and delete price
    And the Store Manager submits the product form
    Then an error message "The price field is required" should be displayed

  @regression
  Scenario: Edit product and delete stock to edit
    When the Store Manager clicks on the "Edit Product" button
    And the Store Manager edit product details and delete stock
    And the Store Manager submits the product form

    Then an error message "The stock field is required" should be displayed

  @regression
  Scenario: Edit product and delete sku to edit
    When the Store Manager clicks on the "Edit Product" button
    And the Store Manager edit product details and delete sku
    And the Store Manager submits the product form
    Then an error message "The sku field is required" should be displayed