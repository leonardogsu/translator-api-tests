Feature: API error responses
  In order to validate the translator API error handling
  As a tester
  I want to check responses for 3xx, 4xx and 5xx status codes

  Background:
    Given the translator API is running

  Scenario: Redirection returns 302 Found
    When I request translation of "banana" to "es-ES"
    Then the response status should be 302

  Scenario: Client error returns 403 Forbidden
    When I request translation of "restricted" to "es-ES"
    Then the response status should be 403

  Scenario: Client error returns 404 Not Found
    When I request translation of "unknown" to "es-ES"
    Then the response status should be 404

  Scenario: Server error returns 500 Internal Server Error
    When I request translation of "error" to "es-ES"
    Then the response status should be 500
