package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public enum UiAttribute {
    URL("/url"),
    LOGIN("/login"),
    PASSWORD("/password");

    private final String value;
    private static final String CONFIG_UI_JSON = "configUI.json";

    UiAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getValueFromJson(UiAttribute attribute) {
        ISettingsFile environment = new JsonSettingsFile(CONFIG_UI_JSON);
        return environment.getValue(attribute.getValue()).toString();
    }
}
