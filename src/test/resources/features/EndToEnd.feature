@EndToEnd
Feature: E2E Order Flow

  Background:
    Given I am on the login page

  Scenario: Customer successfully purchases items with strict assertions
    When I login with valid credentials
    Then I should be redirected to the inventory page
    And The page title should be "Products"
    When I add 2 products to the cart
    And I go to the cart
    Then I should see 2 items in the cart
    When I click checkout
    And I enter personal details "Jane" "Doe" "90210"
    And I click continue
    Then I should see the checkout summary
    When I click finish
    Then I should see the order confirmation "Thank you for your order!"
    And The URL should contain "checkout-complete"
