package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public enum ApiAttribute {
    BASE_URL("/base_url"),
    TOKEN("/token"),
    OWNER_ID("/owner_id"),
    VERSION("/version"),
    IMAGE_PATH("/imagePath");

    private final String value;
    private static final String CONFIG_API_JSON = "configAPI.json";

    ApiAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getValueFromJson(ApiAttribute attribute) {
        ISettingsFile environment = new JsonSettingsFile(CONFIG_API_JSON);
        return environment.getValue(attribute.getValue()).toString();
    }
}
