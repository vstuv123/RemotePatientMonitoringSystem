package resources;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("resources/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}

