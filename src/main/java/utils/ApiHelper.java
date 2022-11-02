package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.path.json.JsonPath;

public class ApiHelper {
    private static final ISettingsFile environment = new JsonSettingsFile("configAPI.json");

    public static String getValueFromJson(Attribute attribute) {
        return environment.getValue(attribute.getValue()).toString();
    }

    public static String getValueFromResponse(String responseBody, String key) {
        JsonPath jsonPath = new JsonPath(responseBody);
        if (responseBody.contains("response")) {
            return jsonPath.get("response." + key).toString();
        } else return jsonPath.get(key).toString();
    }

    public static String makePhotoToSendToWallPost(String ownerId, String photoId) {
        return "photo" + ownerId + "_" + photoId;
    }

    public static String getImagePathFromJson() {
        ISettingsFile environment = new JsonSettingsFile("config.json");
        return environment.getValue("/imagePath").toString();
    }
}
