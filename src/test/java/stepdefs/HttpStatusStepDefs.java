package stepdefs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HttpStatusStepDefs {

    private Response response;

    @When("I request translation of {string} to {string}")
    public void iRequestTranslationWithStatus(String query, String locale) {
        response = given()
                .baseUri("http://localhost:8080")
                .redirects().follow(false)   // importante para 302
                .queryParam("query", query)
                .queryParam("locale", locale)
                .when()
                .get("/");
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        assertThat(response.getStatusCode(), equalTo(expectedStatus));
    }
}
