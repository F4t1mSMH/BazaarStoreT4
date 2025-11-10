@CustomerCartAPI
Feature: Customer Cart API Functionality

  Background:
    Given API base endpoint "/customer/cart"
    And customer has a valid token

  @AddToCart
  Scenario: Verify adding product to customer cart
    When customer sends POST request to add product with id "1"
    Then response status should be 200
    And view cart response should contain "success"

  @ViewCart
  Scenario: Verify retrieving customer cart
    When customer sends GET request to view customer cart
    Then response status should be 200
    And view cart response should contain "items"

  @RemoveFromCart
  Scenario: Verify removing a product from the customer cart
    When customer sends DELETE request to remove product with id "1"
    Then response status should be 200
    And view cart response should contain "removed"

  @ClearCart
  Scenario: Verify clearing all products from the customer cart
    When customer sends POST request to clear all products in the cart
    Then response status should be 200
    And view cart response should contain "cleared"
