package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class JsonPlaceholderClient {
    public static final String BASE_URL = Helper.getBaseUrlFromJson();

    public static Response sendGetRequest(String path) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(path)
                .then()
                .extract().response();
    }

    public static Response sendPostRequest(String json, String path) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(json)
                .post(path);
    }
}
