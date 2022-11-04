package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ApiAttribute;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class VkApiUtils {
    public static final String BASE_URL = ApiAttribute.getValueFromJson(ApiAttribute.BASE_URL);

    public static Response sendGetRequest(String method, Map<String, String> params) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParams(params)
                .queryParam(Parameters.TOKEN, ApiAttribute.getValueFromJson(ApiAttribute.TOKEN))
                .queryParam(Parameters.VERSION, ApiAttribute.getValueFromJson(ApiAttribute.VERSION))
                .post(method);
    }

    public static Response sendGetRequest(String method) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.TOKEN, ApiAttribute.getValueFromJson(ApiAttribute.TOKEN))
                .queryParam(Parameters.VERSION, ApiAttribute.getValueFromJson(ApiAttribute.VERSION))
                .get(method);
    }

    public static Response sendFileToServer(String uploadLink, String imageFullPath) {
        return given()
                .when()
                .contentType(ContentType.MULTIPART)
                .multiPart("photo", new File(imageFullPath))
                .post(uploadLink);
    }
}
