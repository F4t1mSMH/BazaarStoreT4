@Logout
Feature: Logout Functionality
  Background:
    Given user goes to dashboard page

  Scenario: User logs out by hovering over user icon then clicking logout
    When the user moves the cursor to the user icon
    And the user moves the cursor to the Log out Button
    And the user clicks the logout button
    Then the user should be logged out successfully
    And Verify user is redirected to login page after logging out
