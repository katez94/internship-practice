package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderClient {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String ALL_POSTS = "/posts";
    public static final String POST_ID = "/posts/";
    public static final String USERS = "/users";
    public static final String USER_ID = "/users/";

    public static Response getGetAllPostsResponse() {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL + ALL_POSTS)
                .then()
                .extract().response();
    }

    public static Response getPostByIdResponse(int id) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL + POST_ID + id)
                .then()
                .extract().response();
    }

    public static Response getCreatePostResponse(String json) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(json)
                .post(BASE_URL + ALL_POSTS);
    }

    public static Response getAllUsersResponse() {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL + USERS)
                .then().extract().response();
    }

    public static Response getUserByIdResponse(int id) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL + USER_ID + id)
                .then().extract().response();
    }
}
