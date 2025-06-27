Feature: User Registration

  Scenario: Successful registration
    Given I am on the registration page
    And I register a valid user
    When I click the Sign Up button
    Then I should be registered to the page