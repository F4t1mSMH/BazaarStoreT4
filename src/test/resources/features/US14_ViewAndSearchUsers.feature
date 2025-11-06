@US14 @US14_TC001
Feature: US14 - View and Search Users as Admin

  @US14_TC001
  Scenario: Verify that Admin can view all users in the system
    Given the admin is logged into the Bazaar Admin Panel
    When the admin navigates to the Users management page
    Then a list of all registered users is displayed with correct details

  @US14_TC002
  Scenario: Verify that Admin can search users by email
    Given the admin is logged into the Bazaar Admin Panel
    When the admin navigates to the Users management page
    And the admin searches for "customer@sda.com"
    Then the user "customer@sda.com" appears in the search results
  @US14_TC003
  Scenario: Verify that invalid search shows "No user found" message
    Given the admin is logged into the Bazaar Admin Panel
    When the admin navigates to the Users management page
    And the admin searches for "notfound@fakeemail.com"
    Then the message "No user found" is displayed