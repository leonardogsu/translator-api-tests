# Translator API Automated Tests

This repository contains an automated test project for a mocked Translator API, implemented using modern QA/testing technologies:

- Java 17
- Gradle as the build system
- JUnit 5 for test execution
- Cucumber (Gherkin) for BDD (Behavior-Driven Development)
- RestAssured for API test automation
- WireMock for simulating/mocking API endpoints
- SLF4J for logging

The goal of this project is to demonstrate how to design, automate, and document tests for a simple translation API.

## Project Objective

We need to test the following endpoint (simulated via WireMock):

`GET http://localhost:8080?query=apple&locale=es-ES`

Expected response:

`manzana`

Additional scenarios include empty queries and unsupported locales.

## Project Structure
```
translator-tests/
│── build.gradle                 # Gradle dependencies & build configuration
│── settings.gradle              # Gradle project name
│── README.md                    # Project documentation (this file)
│
└── src/
    └── test/
        ├── java/
        │   └── stepdefs/
        │       ├── TranslatorStepDefs.java   # Step definitions (test logic)
        │       └── RunCucumberTest.java      # Test runner (entry point)
        └── resources/
            ├── stepdefs/
            │   └── translator.feature        # Test scenarios in Gherkin
            ├── mappings/                     # WireMock endpoint mocks
            │   ├── apple-to-spanish.json
            │   ├── empty-query.json
            │   └── unsupported-locale.json
            └── junit-platform.properties     # Cucumber runtime configuration
```

## Explanation of Components

### 1. Gherkin Feature File (translator.feature)
Defines test scenarios in plain English:

```
Feature: Translator API

  Scenario: Translate apple to Spanish
    Given the translator API is running
    When I translate "apple" to "es-ES"
    Then the response should be "manzana"
```

### 2. Step Definitions (TranslatorStepDefs.java)
Maps Gherkin steps to Java code.  
Uses RestAssured to call the API and WireMock to mock responses.

### 3. Runner Class (RunCucumberTest.java)
Minimal entry point for executing `.feature` files as tests:
```java
@Cucumber
public class RunCucumberTest {
}
```

### 4. WireMock Mappings (mappings/*.json)
Define API responses without requiring a real backend.

Example:
```json
{
  "request": {
    "method": "GET",
    "urlPath": "/",
    "queryParameters": {
      "query": { "equalTo": "apple" },
      "locale": { "equalTo": "es-ES" }
    }
  },
  "response": {
    "status": 200,
    "headers": { "Content-Type": "text/plain" },
    "body": "manzana"
  }
}
```

### 5. junit-platform.properties
Configures Cucumber runtime:
```
cucumber.glue=stepdefs
cucumber.plugin=pretty, html:build/reports/cucumber-report.html, json:build/reports/cucumber-report.json
```

## How to Run the Tests

1. Clone the repository
```
git clone https://github.com/<your-username>/translator-tests.git
cd translator-tests
```

2. Run tests with Gradle

Windows (PowerShell):
```
.\gradlew clean test
```

Linux/Mac:
```
./gradlew clean test
```

## Test Reports
After execution, reports will be generated:

- [Cucumber HTML Report](./build/reports/cucumber-report.html)
- [Cucumber JSON Report](./build/reports/cucumber-report.json)
- [JUnit Report (Gradle)](./build/reports/tests/test/index.html)

Note: Links work only after running tests locally.

## Technologies Used
- Java 17 → programming language
- Gradle → build automation tool
- JUnit 5 → testing framework
- Cucumber (BDD) → human-readable test cases in Gherkin
- RestAssured → API testing library
- WireMock → mock HTTP server
- SLF4J + Simple Logger → logging

## Example Scenario Executions
- Translate apple to Spanish → Response: manzana
- Empty query → Response: invalid query
- Unsupported locale → Response: unsupported locale

## Why this project?
- Demonstrates clean automation architecture.
- Uses BDD for readability across technical and non-technical users.
- Employs mocking to avoid dependency on real APIs.
- Generates reports for HR, QA, and developers.  
