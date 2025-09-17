package stepdefs;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.Before;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Hooks {
    private static WireMockServer wireMockServer;

    @Before(order = 0)
    public void setupMockServer() {
        if (wireMockServer == null || !wireMockServer.isRunning()) {
            wireMockServer = new WireMockServer(
                    options().port(8080).usingFilesUnderDirectory("src/test/resources")
            );
            wireMockServer.start();
        }
    }
}
