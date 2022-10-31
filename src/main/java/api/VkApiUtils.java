package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class VkApiUtils {
    public static final String BASE_URL = ApiHelper.getBaseUrl();

    public static Response createPostOnTheWall(String ownerId, String randomMessage) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.OWNER_ID, ownerId)
                .queryParam(Parameters.MESSAGE, randomMessage)
                .queryParam(Parameters.TOKEN, ApiHelper.getToken())
                .queryParam(Parameters.VERSION, ApiHelper.getVersion())
                .post(Methods.WALL_POST);
    }

    public static Response getWallUploadServer() {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.TOKEN, ApiHelper.getToken())
                .queryParam(Parameters.VERSION, ApiHelper.getVersion())
                .post(Methods.WALL_UPLOAD_SERVER);
    }

    public static Response sendFileToServer(String uploadLink, String imageFullPath) {
        return given()
                .urlEncodingEnabled(false)
                .when()
                .contentType(ContentType.MULTIPART)
                .multiPart("photo", new File(imageFullPath))
                .post(uploadLink);
    }

    public static Response saveWallPhoto(String server, String photo, String hash) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.SERVER, server)
                .queryParam(Parameters.PHOTO, photo)
                .queryParam(Parameters.HASH, hash)
                .queryParam(Parameters.TOKEN, ApiHelper.getToken())
                .queryParam(Parameters.VERSION, ApiHelper.getVersion())
                .post(Methods.SAVE_WALL_PHOTO);
    }

    public static Response editPostOnTheWall(String ownerId, String postId, String text, String attachment) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.OWNER_ID, ownerId)
                .queryParam(Parameters.POST_ID, postId)
                .queryParam(Parameters.MESSAGE, text)
                .queryParam(Parameters.ATTACHMENTS, attachment)
                .queryParam(Parameters.TOKEN, ApiHelper.getToken())
                .queryParam(Parameters.VERSION, ApiHelper.getVersion())
                .post(Methods.WALL_POST_EDIT);
    }

    public static Response addCommentToWallPost(String ownerId, String postId, String text) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.OWNER_ID, ownerId)
                .queryParam(Parameters.POST_ID, postId)
                .queryParam(Parameters.MESSAGE, text)
                .queryParam(Parameters.TOKEN, ApiHelper.getToken())
                .queryParam(Parameters.VERSION, ApiHelper.getVersion())
                .post(Methods.CREATE_COMMENT);
    }

    public static Response isItemLiked(String userId, String postId, String type) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.USER_ID, userId)
                .queryParam(Parameters.ITEM_ID, postId)
                .queryParam(Parameters.TYPE, type)
                .queryParam(Parameters.TOKEN, ApiHelper.getToken())
                .queryParam(Parameters.VERSION, ApiHelper.getVersion())
                .post(Methods.IS_LIKED);
    }

    public static Response deleteWallPost(String ownerId, String postId) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam(Parameters.OWNER_ID, ownerId)
                .queryParam(Parameters.POST_ID, postId)
                .queryParam(Parameters.TOKEN, ApiHelper.getToken())
                .queryParam(Parameters.VERSION, ApiHelper.getVersion())
                .post(Methods.DELETE_WALL_POST);
    }
}
