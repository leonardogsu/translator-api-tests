package stepdefs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TranslatorStepDefs {

    private Response response;

    @Given("the translator API is running")
    public void theTranslatorApiIsRunning() {
        // WireMock is started by Hooks.java
    }

    @When("I translate {string} to {string}")
    public void iTranslateTo(String query, String locale) {
        response = given()
                .baseUri("http://localhost:8080")
                .queryParam("query", query)
                .queryParam("locale", locale)
                .when()
                .get("/");
    }

    @Then("the response should be {string}")
    public void theResponseShouldBe(String expected) {
        assertThat(response.getBody().asString(), equalTo(expected));
    }
}
