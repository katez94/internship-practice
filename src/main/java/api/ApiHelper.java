package api;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.path.json.JsonPath;

public class ApiHelper {
    private static final ISettingsFile environment = new JsonSettingsFile("configAPI.json");
    private static final String BASE_URL = "/base_url";
    private static final String TOKEN = "/token";
    private static final String OWNER_ID = "/owner_id";
    private static final String VERSION = "/version";

    public static String getBaseUrl() {
        return environment.getValue(BASE_URL).toString();
    }

    public static String getToken() {
        return environment.getValue(TOKEN).toString();
    }

    public static String getOwnerId() {
        return environment.getValue(OWNER_ID).toString();
    }

    public static String getVersion() {
        return environment.getValue(VERSION).toString();
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
