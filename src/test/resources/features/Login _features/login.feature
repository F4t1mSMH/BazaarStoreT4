@Regression @Login
Feature: Login Functionality

  Background:
    Given user goes to homepage


  @Smoke
  Scenario: User login using a valid email and password
    When user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    Then user should be logged in successfully

  @Negative
  Scenario: User login using incorrect password
    When user enters email "customer@sda.com" and password "WrongPassword"
    And user clicks login button
    Then user should see error message
    And user should remain on login page

  @Negative
  Scenario: User login using invalid email format
    When user enters email "invalidemail" and password "Password123"
    And user clicks login button
    Then user should see invalid email format validation message
    And user should remain on login page

  @Negative
  Scenario: Login with empty email field
    When user enters email "" and password "Password123"
    And user clicks login button
    Then user should see empty "email" error message

  @Negative
  Scenario: Login with empty password field
    When user enters email "customer@sda.com" and password ""
    And user clicks login button
    Then user should see empty "password" error message

  @Smoke
  Scenario: Verify successful login redirects user to dashboard
    When user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    Then user should be logged in successfully

  @API @Smoke
  Scenario: Verify login via API with valid credentials
    When user logs in via API with valid credentials
    Then API should return success status code

  @Regression @Login @API
  Scenario: Successful login via API
    Given API endpoint "/login"
    When user sends POST request with valid credentials
    Then response status code should be 200
    And response should contain "token"
    And print the actual token

  @Regression @Login @Negative @API
  Scenario: Login via API with invalid password
    Given API endpoint "/login"
    When user sends POST request with invalid password
    Then response status code should be 401
    And response body should contain "Invalid credentials"

