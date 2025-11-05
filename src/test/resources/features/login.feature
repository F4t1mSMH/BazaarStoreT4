@Regression @Login
Feature: Login Functionality

  Background:
    Given user goes to homepage

  @Smoke @Customer
  Scenario: Successful login with valid customer credentials from config
    When user enters valid customer credentials
    And user clicks login button
    Then user should be logged in successfully

  @Smoke
  Scenario: Successful login with valid credentials
    When user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    Then user should be logged in successfully

  @Negative
  Scenario: Login with invalid credentials
    When user enters email "invalid@test.com" and password "WrongPassword"
    And user clicks login button
    Then user should see error message
    And user should remain on login page



  @API @Smoke
  Scenario: Verify login via API
    When user logs in via API with valid credentials
    Then API should return success status code

  @Admin
  Scenario: Successful login as Admin
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @StoreManager
  Scenario: Successful login as Store Manager
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @Negative
  Scenario: Login with empty email
    When user enters email "" and password "Password.12345"
    And user clicks login button
    Then user should see empty "email" error message

  @Negative
  Scenario: Login with empty password
    When user enters email "customer@sda.com" and password ""
    And user clicks login button
    Then user should see empty "password" error message