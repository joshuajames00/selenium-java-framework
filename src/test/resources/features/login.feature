Feature: User Authentication
  As a registered user
  I want to log in to the application
  So that I can access the secure area

  @smoke @login
  Scenario: Successful login with valid credentials
    Given the user navigates to the login page
    When the user logs in with username "tomsmith" and password "SuperSecretPassword!"
    Then the secure area should be displayed
    And a login success message should be shown

  @regression @login
  Scenario: Failed login with invalid username
    Given the user navigates to the login page
    When the user logs in with username "invalid_user" and password "SuperSecretPassword!"
    Then an error message should indicate the credentials are invalid

  @regression @login
  Scenario: Failed login with invalid password
    Given the user navigates to the login page
    When the user logs in with username "tomsmith" and password "wrong_password"
    Then an error message should indicate the credentials are invalid
