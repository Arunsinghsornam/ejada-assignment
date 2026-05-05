# =============================================
# Feature: Simple Books API Tests
# =============================================

@api
Feature: Simple Books API
  As a tester
  I want to validate the Simple Books API
  So that I can ensure all endpoints work correctly

  @api-status
  Scenario: Verify API status is healthy
    Given the Simple Books API is running
    Then the API status should be "OK"

  @api-get-books
  Scenario: Get all books
    Given the Simple Books API is running
    When I send a GET request to "/books"
    Then the response status code should be 200
    And the response should contain a list of books

  @api-get-books-fiction
  Scenario: Get only fiction books
    Given the Simple Books API is running
    When I send a GET request to "/books?type=fiction"
    Then the response status code should be 200
    And all returned books should be of type "fiction"

  @api-get-single-book
  Scenario: Get a single book by ID
    Given the Simple Books API is running
    When I send a GET request to "/books/1"
    Then the response status code should be 200
    And the response should contain book details

  @api-auth
  Scenario: Register API client and get token
    Given the Simple Books API is running
    When I register with client name "EjadaTester" and email "ejada_test@example.com"
    Then the response status code should be 201
    And I should receive an access token

  @api-order
  Scenario: Create a book order with authentication
    Given the Simple Books API is running
    And I am authenticated with the API
    When I create an order for book ID 1 with customer name "John Doe"
    Then the response status code should be 201
    And the order should be created successfully

  @api-get-orders
  Scenario: Get all orders with authentication
    Given the Simple Books API is running
    And I am authenticated with the API
    When I send an authenticated GET request to "/orders"
    Then the response status code should be 200

  @api-patch-order
  Scenario: Update an existing order
    Given the Simple Books API is running
    And I am authenticated with the API
    And I have an existing order for book ID 2 with customer name "Update Test"
    When I update the order customer name to "Updated Customer"
    Then the response status code should be 204

  @api-delete-order
  Scenario: Delete an existing order
    Given the Simple Books API is running
    And I am authenticated with the API
    And I have an existing order for book ID 3 with customer name "Delete Test"
    When I delete the order
    Then the response status code should be 204
    And the order should no longer exist
