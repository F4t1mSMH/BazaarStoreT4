 @CustomerUS
Feature: Favorite Products Functionality

  @US07_TC001
  Scenario: Verify customer can mark a product as favorite
#    Given User is already logged in
    And Customer is on the customer page
    When Customer clicks the heart icon on a product
    Then The product should be marked as favorite
    And The heart icon should change to filled

  @US07_TC002
  Scenario: Verify error message appears if Customer marks same product as favorite again
#    Given User is already logged in
    And Customer is on the customer page
    When Customer clicks the heart icon on a product
    And Customer clicks the heart icon on the same product again
    Then Error message "Product is already in favorites." should appear