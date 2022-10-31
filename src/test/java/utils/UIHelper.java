package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class UIHelper {
    private static final ISettingsFile environment = new JsonSettingsFile("configUI.json");

    public static String getUrl() {
        return environment.getValue("/url").toString();
    }

    public static String getLogin() {
        return environment.getValue("/login").toString();
    }

    public static String getPassword() {
        return environment.getValue("/password").toString();
    }
}
