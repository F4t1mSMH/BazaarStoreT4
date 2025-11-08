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

  @US07_TC003
  Scenario: Verify accessing favorite list
    And Customer is on the customer page
    When Customer clicks the heart icon on a product
    Given Customer navigates to My Favorites page
    When  Customer has at least one product marked as favorite
    Then All favorite products should appear on Favorites page


  @US07_TC004
  Scenario: Verify removing product from "My Favorites" list
    And Customer is on the customer page
    When Customer clicks the heart icon on a product
    Given Customer navigates to My Favorites page
    When Customer clicks the heart icon on a favorite product on My Favorites page
    Then The product should be removed from Favorites list
    And Heart icon status should update on the home page
    And Empty message should be displayed on the page
