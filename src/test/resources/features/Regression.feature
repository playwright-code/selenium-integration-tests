@Regression
Feature: Regression Tests

  Scenario: Verify Inventory UI Elements
    Given I am on the login page
    When I login with valid credentials
    Then I should see the product list
    And The cart badge should be empty
    When I add 1 products to the cart
    Then The cart badge should display "1"
