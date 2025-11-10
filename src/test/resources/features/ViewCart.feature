@ViewCart
Feature: Cart functionality - Add, View, Remove, and Confirm items

  Background:
    Given customer is logged in and on the dashboard


  # US06_TC001 - Add product to the cart

  @US06
  Scenario: Verify adding a product to the cart
    When customer adds a product to the cart
    Then customer hovers over the cart icon
    And customer clicks the View Cart link
    Then the cart page should display products, prices




  # US08_TC001 - View items in the cart

  @US08
  Scenario: Verify that the cart displays added items
    When customer adds a product to the cart
    And customer hovers over the cart icon
    And customer clicks the View Cart link
    Then the cart page should display products, prices


  # US06_TC002 - Remove product from the cart

  @US06
  Scenario: Verify removing an item from the cart
  When customer adds a product to the cart
    And customer hovers over the cart icon
    And customer clicks the View Cart link
    And customer hovers over the cart icon again
    And customer removes the item from the cart
    Then a success message should appear with the text "Item Removed!"


  # US09_TC001 - Confirm items in the cart

  @US09
  Scenario: Verify cart confirmation process
    When customer adds a product to the cart
    And customer hovers over the cart icon
    And customer clicks the View Cart link
    Then the cart page should display products, prices
    When customer clicks the Confirm Cart button
    Then a success message should appear with the title "Success!"
