package db;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public enum JsonAttributes {
    DB_URL("/dbUrl"),
    DB_USER("/dbUser"),
    DB_PASSWORD("/dbPassword");

    private final String value;

    private static final String CONFIG_API_JSON = "dbConfig.json";

    JsonAttributes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getValueFromJson(JsonAttributes attribute) {
        ISettingsFile environment = new JsonSettingsFile(CONFIG_API_JSON);
        return environment.getValue(attribute.getValue()).toString();
    }
}
