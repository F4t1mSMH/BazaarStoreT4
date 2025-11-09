@CustomerUS
Feature: US04 - Browse products as a customer

  @US04_TC002 @Customer
  Scenario: Verify customer can see products
    Given Customer is on the customer page
    When Customer observes each product card
    Then All products should be visible

  @US04_TC003
  Scenario: Verify products load quickly with details
    Given Customer is on the customer page
    When Products are loaded
    Then All products should load in less than 3 seconds
    And Each product should display name, price, image, and description