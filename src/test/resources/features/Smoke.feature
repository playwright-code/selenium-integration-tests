@Smoke
Feature: Smoke Test - Critical Components

  Scenario: Verify Login and Footer Elements
    Given I am on the login page
    When I login with valid credentials
    Then I should be redirected to the inventory page
    And I should see the "Twitter" social link
    And I should see the "Facebook" social link
