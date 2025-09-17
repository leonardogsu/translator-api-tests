Feature: Translator API
  In order to validate translation behavior
  As a tester
  I want to check successful translations and business errors

  Background:
    Given the translator API is running

  Scenario: Translate apple to Spanish
    When I translate "apple" to "es-ES"
    Then the response should be "manzana"

  Scenario: Empty query
    When I translate "" to "es-ES"
    Then the response should be "invalid query"

  Scenario: Unsupported locale
    When I translate "apple" to "xx-XX"
    Then the response should be "unsupported locale"
