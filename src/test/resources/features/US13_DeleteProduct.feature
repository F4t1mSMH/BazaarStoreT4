@Manager @Delete
Feature: Product Deletion

  @Smoke @Regression
  Scenario: Successful API delete and verification
    Given a valid product payload is prepared
    When a POST request is sent to create the product via API
    Then the product is created successfully and product ID is stored

    When a DELETE request is sent to delete the product via API
    Then the product is deleted successfully via API

  @Regression @Cancel
  Scenario: Verify product remains after cancelling deletion
    Given a product is created successfully via API and its ID is available
    When the Manager click on product page
    And the Store Manager clicks the delete button for the product
    And the Store Manager cancels the deletion
    Then the product should remain visible on the Products page

  @Regression @Delete
  Scenario: Successfully delete a product as a store manager
    Given a product is created successfully via API and its ID is available
    When the Manager click on product page
    And the Store Manager clicks the delete button for the product
    And the Store Manager confirms the deletion
    Then a success message "Product deleted successfully!" should be displayed
    And the product should not be visible on the Products page