package client;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class Helper {
    public static String getBaseUrlFromJson() {
        ISettingsFile environment = new JsonSettingsFile("apiConfig.json");
        return environment.getValue("/baseUrl").toString();
    }
}
