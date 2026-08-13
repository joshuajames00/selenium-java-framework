Feature: Checkbox Interactions
  As a user
  I want to interact with checkboxes
  So that I can select and deselect options

  Background:
    Given the user navigates to the checkboxes page

  @smoke @checkboxes
  Scenario: Check an unchecked checkbox
    When the user checks checkbox 1
    Then checkbox 1 should be checked

  @regression @checkboxes
  Scenario: Uncheck a checked checkbox
    When the user unchecks checkbox 2
    Then checkbox 2 should not be checked
