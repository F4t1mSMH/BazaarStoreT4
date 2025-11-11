@delete
Feature: Product Deletion
  Background:
    Given the store manger logged in on the dashboard page
    And the manger click on product page

  @deleteProduct_API
  Scenario: Successful API delete and verification
    Given a valid product payload is prepared
    When a POST request is sent to create the product via API
    Then the product is created successfully and product ID is stored

    When a DELETE request is sent to delete the product via API
    Then the product is deleted successfully via API

  @deleteProduct
  Scenario: Successful delete a product as a store manager
    Given a product is created successfully via API and its ID is available
    When the Store Manager searches for the newly created product by name
    And the Store Manager clicks the delete button for the product
    And the Store Manager confirms the deletion
    Then a success message "Product Deleted successfully!" should be displayed
    And the product should not be visible on the Products page

  @cancel
  Scenario: Successful cancel delete operation as a store manager
    Given a product is created successfully via API and its ID is available
    When the Store Manager searches for the newly created product by name
    And the Store Manager clicks the delete button for the product
    And the Store Manager cancels the deletion
    Then the product should remain visible on the Products page