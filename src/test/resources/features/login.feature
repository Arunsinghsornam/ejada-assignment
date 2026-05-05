# =============================================
# Feature: SauceDemo Login Scenarios
# =============================================

@login
Feature: Login Functionality
  As a user of SauceDemo
  I want to be able to log in to the application
  So that I can access the product inventory

  Background:
    Given I am on the login page

  @smoke @valid-login
  Scenario: Successful login with valid credentials
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the inventory page
    And the page title should be "Products"

  @invalid-login
  Scenario: Login with invalid username
    When I login with username "invalid_user" and password "secret_sauce"
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  @invalid-login
  Scenario: Login with invalid password
    When I login with username "standard_user" and password "wrong_password"
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  @invalid-login
  Scenario: Login with locked out user
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."

  @invalid-login
  Scenario: Login with empty username
    When I login with username "" and password "secret_sauce"
    Then I should see an error message "Epic sadface: Username is required"

  @invalid-login
  Scenario: Login with empty password
    When I login with username "standard_user" and password ""
    Then I should see an error message "Epic sadface: Password is required"

  @invalid-login
  Scenario: Login with empty credentials
    When I login with username "" and password ""
    Then I should see an error message "Epic sadface: Username is required"

  @performance-user
  Scenario: Successful login with performance glitch user
    When I login with username "performance_glitch_user" and password "secret_sauce"
    Then I should be redirected to the inventory page

  @problem-user
  Scenario: Successful login with problem user
    When I login with username "problem_user" and password "secret_sauce"
    Then I should be redirected to the inventory page

  @data-driven
  Scenario Outline: Login with multiple valid users
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the inventory page

    Examples:
      | username              | password     |
      | standard_user         | secret_sauce |
      | error_user            | secret_sauce |
      | visual_user           | secret_sauce |
