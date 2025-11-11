@Registration
Feature: Registration Feature

  Background:go to homepage and clck on sgn up
    Given user goes to homepage
    When user clicks registration link

  @HappyPathRegistration
  Scenario: Registration Happy Path
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see success message for registration
    And assert the registration via API

  @NegativeRegistration @invalidEmail
  Scenario: Registration Negative
    And user enters email for sign up "invalid_email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see invalid email error message
    And assert the negative registration via API using email "invalid_email.com"


  @NegativeRegistration @EmptyEmail
  Scenario: Registration Negative 1
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see empty email error message

  @NegativeRegistration @EmptyName
  Scenario: Registration Negative 2
    And user enters email for sign up "faker"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see empty full name error message

  @NegativeRegistration @EmptyPasswoord
  Scenario: Registration Negative 3
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see empty password error message

  @NegativeRegistration @PasswordLTS
  Scenario: Registration Negative 3
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters password less than six characters for sign up "John1"
    #And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see password error message


  @NegativeRegistration @PasswordDNMatch
  Scenario: Registration Negative 4
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters "password" not equal the "confirm_password" for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see password do not match error message

  @NegativeRegistration @invalidName1
  Scenario: Registration Negative 5
    And user enters email for sign up "faker"
    And user enters full name for sign up with special caracter "John Doe@"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see invalid name error message

  @NegativeRegistration @invalidName2
  Scenario: Registration Negative 6
    And user enters email for sign up "faker"
    And user enters full name for sign up with Number "John Doe1"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sing up button
    Then user should see invalid name error message