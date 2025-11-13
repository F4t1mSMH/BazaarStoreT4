Feature: View product details
  @US05_TC001 @regression @CustomerUI
  Scenario: Verify that customer can view one product details
    Given Customer is on the customer page
    When Customer clicks on a product
    Then Product details should be displayed including Name, Price, Description, and Images

# ---------------------------------------APi--------------------------------------------------

  @US05_TC002 @API @Smoke
  Scenario: Verify that customer can view one product details via API
    When  assert the customer can view specific product with its details