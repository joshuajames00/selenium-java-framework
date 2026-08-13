Feature: Dropdown Selection
  As a user
  I want to select options from a dropdown list
  So that I can choose from available options

  @smoke @dropdown
  Scenario: Select options from the dropdown
    Given the user navigates to the dropdown page
    When the user selects "Option 1" from the dropdown
    Then "Option 1" should be selected in the dropdown
    When the user selects "Option 2" from the dropdown
    Then "Option 2" should be selected in the dropdown
