Feature: Add and remove elements
  As a user, I want to add and remove dynamic elements on the page

  @regression @add_remove
  Scenario: Add and remove elements successfully
    Given I am on the add-remove elements page
    When I add 3 elements
    Then I should see 3 elements
    When I remove 1 elements
    Then I should see 2 elements
