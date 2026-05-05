# =============================================
# Feature: SauceDemo Product Order Scenario
# =============================================

@order
Feature: Product Order Flow
  As a logged-in user of SauceDemo
  I want to add a product to the cart and complete checkout
  So that I can successfully place an order

  Background:
    Given I am on the login page
    And I login with username "standard_user" and password "secret_sauce"
    And I am on the inventory page

  @smoke @add-to-cart
  Scenario: Add first product to cart
    When I add the first product to the cart
    Then the cart count should be 1

  @smoke @full-order
  Scenario: Complete a full product order
    When I add the first product to the cart
    And I navigate to the cart
    Then the cart should contain 1 item
    When I proceed to checkout
    And I fill in checkout information with first name "John" last name "Doe" and postal code "12345"
    And I click continue
    Then I should be on the checkout overview page
    When I click finish
    Then the order should be confirmed with message "Thank you for your order!"

  @specific-product
  Scenario: Add specific product "Sauce Labs Backpack" and complete order
    When I add product "Sauce Labs Backpack" to the cart
    And I navigate to the cart
    Then the cart should contain product "Sauce Labs Backpack"
    When I proceed to checkout
    And I fill in checkout information with first name "Jane" last name "Smith" and postal code "54321"
    And I click continue
    Then I should be on the checkout overview page
    When I click finish
    Then the order should be confirmed with message "Thank you for your order!"
