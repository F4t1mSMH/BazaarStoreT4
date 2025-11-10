@CustomerUS
Feature: View product details

  Scenario: Verify that customer can view one product details
    Given Customer is on the customer page
    When Customer clicks on a product
    Then Product details should be displayed including Name, Price, Description, and Images
