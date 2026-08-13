Feature: Secure Area
  As an authenticated user
  I want to access the secure area
  So that I can use protected functionality

  Background:
    Given the user navigates to the login page
    And the user logs in with valid credentials

  @smoke @secure
  Scenario: Secure area is accessible after login
    Then the secure area should be displayed

  @regression @secure
  Scenario: User can log out from the secure area
    When the user logs out
    Then the user should be on the login page
