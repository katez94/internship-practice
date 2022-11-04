package utils;

import io.restassured.path.json.JsonPath;

public class ApiHelper {
    public static String getValueFromResponse(String responseBody, String key) {
        JsonPath jsonPath = new JsonPath(responseBody);
        if (responseBody.contains("response")) {
            return jsonPath.get("response." + key).toString();
        } else return jsonPath.get(key).toString();
    }

    public static String makePhotoToSendToWallPost(String ownerId, String photoId) {
        return String.format("photo%1$s_%2$s", ownerId, photoId);
    }
}
