package steps;

import api.Keys;
import api.Methods;
import api.Parameters;
import api.VkApiUtils;
import api.models.SavedPhoto;
import com.google.gson.Gson;
import io.restassured.response.Response;
import utils.ApiHelper;
import utils.RegexHelper;

import java.util.HashMap;
import java.util.Map;

public class ApiSteps {
    public static String getPhotoId(String ownerId, String imageFullPath) {
        Response getWallUploadServerResponse = getWallUploadServer();
        String uploadLink = ApiHelper.getValueFromResponse(getWallUploadServerResponse.getBody().asString(), Keys.UPLOAD_URL);
        Response sendFileToServerResponse = VkApiUtils.sendFileToServer(uploadLink, imageFullPath);
        SavedPhoto savedPhoto = new Gson().fromJson(sendFileToServerResponse.getBody().asString(), SavedPhoto.class);
        Response saveWallPhotoResponse = saveWallPhoto(savedPhoto);
        String photoId = ApiHelper.getValueFromResponse(saveWallPhotoResponse.getBody().asString(), Keys.ID);
        photoId = RegexHelper.removeAllButNumbers(photoId);
        return ApiHelper.makePhotoToSendToWallPost(ownerId, photoId);
    }

    public static String addCommentToWallPost(String ownerId, String postId, String text) {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.OWNER_ID, ownerId);
        params.put(Parameters.POST_ID, postId);
        params.put(Parameters.MESSAGE, text);
        Response response = VkApiUtils.sendGetRequest(Methods.CREATE_COMMENT, params);
        return ApiHelper.getValueFromResponse(response.getBody().asString(), Keys.COMMENT_ID);
    }

    public static Response createPostOnTheWall(String ownerId, String randomMessage) {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.OWNER_ID, ownerId);
        params.put(Parameters.MESSAGE, randomMessage);
        return VkApiUtils.sendGetRequest(Methods.WALL_POST, params);
    }

    public static Response getWallUploadServer() {
        return VkApiUtils.sendGetRequest(Methods.WALL_UPLOAD_SERVER);
    }

    public static Response editPostOnTheWall(String ownerId, String postId, String text, String attachment) {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.OWNER_ID, ownerId);
        params.put(Parameters.POST_ID, postId);
        params.put(Parameters.MESSAGE, text);
        params.put(Parameters.ATTACHMENTS, attachment);
        return VkApiUtils.sendGetRequest(Methods.WALL_POST_EDIT, params);
    }

    public static Response isItemLiked(String userId, String postId, String type) {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.USER_ID, userId);
        params.put(Parameters.ITEM_ID, postId);
        params.put(Parameters.TYPE, type);
        return VkApiUtils.sendGetRequest(Methods.IS_LIKED, params);
    }

    public static Response deleteWallPost(String ownerId, String postId) {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.OWNER_ID, ownerId);
        params.put(Parameters.POST_ID, postId);
        return VkApiUtils.sendGetRequest(Methods.DELETE_WALL_POST, params);
    }

    public static Response saveWallPhoto(SavedPhoto savedPhoto) {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.SERVER, savedPhoto.getServer());
        params.put(Parameters.PHOTO, savedPhoto.getPhoto());
        params.put(Parameters.HASH, savedPhoto.getHash());
        return VkApiUtils.sendGetRequest(Methods.SAVE_WALL_PHOTO, params);
    }
}
