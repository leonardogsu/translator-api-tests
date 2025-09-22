package stepdefs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final String USE_MOCK = System.getProperty("useMock", "true");
    private static final String REAL_URL;

    static {
        Properties props = new Properties();
        String url = null;
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
                url = props.getProperty("realUrl");
            } else {
                System.err.println("⚠️ No se encontró config.properties en resources, se usará valor por defecto");
            }
        } catch (IOException e) {
            System.err.println("⚠️ Error leyendo config.properties: " + e.getMessage());
        }

        // La prioridad es: -DrealUrl > config.properties > default
        REAL_URL = System.getProperty("realUrl", url != null ? url : "http://localhost:8081");
    }

    public static boolean isMockEnabled() {
        return USE_MOCK.equalsIgnoreCase("true");
    }

    public static String getBaseUri() {
        return isMockEnabled() ? "http://localhost:8080" : REAL_URL;
    }
}
