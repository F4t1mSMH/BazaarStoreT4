@Users @Admin
Feature: User Management in Bazaar Admin Panel
  Background:
    Given the admin is logged into the Bazaar Admin Panel
    And the admin navigates to the Users management page
  @US14_TC001
  Scenario: Verify that Admin can view all users in the system
    When the admin navigates to the Users management page
    Then a list of all registered users is displayed with correct details

  @US14_TC002
  Scenario: Verify that Admin can search users by email
    When the admin navigates to the Users management page
    And the admin searches for "customer@sda.com"
    Then the user "customer@sda.com" appears in the search results
  @US14_TC003
  Scenario: Verify that invalid search shows "No user found" message
    When the admin navigates to the Users management page
    And the admin searches for "notfound@fakeemail.com"
    Then the message "No user found" is displayed

  @AddUser @Smoke
  @US15_TC001
  Scenario: Add a new user as an Admin
    When the admin clicks Add User
    And the admin fills the user form with:
      | name           | email            | role       | password         | confirmPassword |
      | sara  | sarah97@mail.com | Customer  | Test@12345       | Test@12345      |
    And the admin submits the user form
    And the new user "sarah97@mail.com" should appear in the user list
  @US15_TC002
  Scenario: Verify missing email validation
    And the admin navigates to the Users management page
    When the admin clicks Add User
    And the admin fills the user form with:
      | name | email | role | password | confirmPassword |
      | Test |       | Customer | Test@12345 | Test@12345 |
    And the admin submits the user form
    Then the error message "The email field is required" should be displayed
  @US15_TC003
  Scenario: Verify invalid email format
    And the admin navigates to the Users management page
    When the admin clicks Add User
    And the admin fills the user form with:
      | name | email         | role     | password | confirmPassword |
      | Test | sarah_test.com | Customer | Test@12345 | Test@12345 |
    And the admin submits the user form


  @EditUser   @Smoke

  @US16_TC001
  Scenario: Verify editing user details successfully
    When the admin clicks "Edit" on a user
    And the admin modifies the user email to "updated_sa@mail.com"
    And the admin clicks "Save"
    Then a success message "User details updated successfully." should be displayed
    And the user email in the table should be "updated_sa@mail.com"


  @DeleteUser
  Scenario: Delete existing user
    When the admin clicks Delete beside user "updated_sa@mail.com"
    And the admin confirms deletion

  @CancelDeleteUser
  Scenario: Cancel deletion of a user
    When the admin clicks Delete beside user "updated_sa@mail.com"
    And the admin cancels deletion
    Then the user "updated_sa@mail.com" should remain in the list