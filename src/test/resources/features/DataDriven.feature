@DataDriven
Feature: Data Driven Login via CSV

  Scenario Outline: Login with various user roles from CSV
    Given I am on the login page
    When I login using CSV credentials for role "<role>"
    Then I should be logged in or see an error based on "<role>"

    Examples:
      | role     |
      | standard |
      | locked   |
      | problem  |
