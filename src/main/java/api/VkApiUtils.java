package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ApiHelper;
import utils.Attribute;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class VkApiUtils {
    public static final String BASE_URL = ApiHelper.getValueFromJson(Attribute.BASE_URL);

    public static Response sendPostRequest(String method, Map<String, String> params) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParams(params)
                .queryParam(Parameters.TOKEN, ApiHelper.getValueFromJson(Attribute.TOKEN))
                .queryParam(Parameters.VERSION, ApiHelper.getValueFromJson(Attribute.VERSION))
                .post(method);
    }

    public static Response sendPostRequest(String method) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.TOKEN, ApiHelper.getValueFromJson(Attribute.TOKEN))
                .queryParam(Parameters.VERSION, ApiHelper.getValueFromJson(Attribute.VERSION))
                .post(method);
    }

    public static Response sendFileToServer(String uploadLink, String imageFullPath) {
        return given()
                .when()
                .contentType(ContentType.MULTIPART)
                .multiPart("photo", new File(imageFullPath))
                .post(uploadLink);
    }
}
