Feature: Product Deletion
  Background: user goes to product page

  @delete @smoke
  Scenario: Successful delete a product as a store manager
    When the Store Manager clicks on the "Delete" button
    And a Yes, delete it! message should appear Click on it
    Then a success message "Product Deleted successfully!" should be displayed

  @smoke
  Scenario: Successful delete a product as a store manager
      When the Store Manager clicks on the "Delete" button
      And  Click on cancel
      Then the product should still be in the product page
