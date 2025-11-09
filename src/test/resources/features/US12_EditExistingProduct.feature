Feature: Product Edit
  Background: user goes to product page

  @edit
  Scenario: Successful edit product as a store manager
    When the Store Manager clicks on the "Edit" button
    And the Store Manager edit product details
    And the Store Manager submits the product form
    Then the edited product "<Name>" should appear in the product list
    And a success message "Product updated successfully" should be displayed

  @Negative
  Scenario: Edit product and delete name
    When the Store Manager clicks on the "Edit" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The name field is required" should be displayed


  @Negative
  Scenario: Edit product and delete empty price
    When the Store Manager clicks on the "Edit" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The price field is required" should be displayed

  @Negative
  Scenario: Edit product and delete stock
    When the Store Manager clicks on the "Edit" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The stock field is required" should be displayed

  @Negative
  Scenario: Edit product and delete sku
    When the Store Manager clicks on the "Edit" button
    And the Store Manager enters product details
    And the Store Manager submits the product form
    Then an error message "The sku field is required" should be displayed
