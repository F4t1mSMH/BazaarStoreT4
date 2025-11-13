Feature: US04 - Browse products as a customer

  @US04_TC001  @regression @CustomerUI
  Scenario: Verify customer can see products
    Given Customer is on the customer page
    When Customer observes each product card
    Then All products should be visible

  @US04_TC002  @regression @CustomerUI
  Scenario: Verify products load quickly with details
    Given Customer is on the customer page
    When Products are loaded
    Then All products should load in less than 3 seconds
    And Each product should display name, price, image, and description

# ---------------------------------------APi--------------------------------------------------

  @US04_TC003   @API @Smoke
  Scenario: Verify customer can see all products via API
    When assert the Customer can see products via API
