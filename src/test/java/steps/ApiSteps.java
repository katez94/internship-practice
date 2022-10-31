package steps;

import api.ApiHelper;
import api.Keys;
import api.VkApiUtils;
import io.restassured.response.Response;
import utils.TestHelper;

public class ApiSteps {
    public static String getPhotoId(String ownerId, String imageFullPath) {
        Response getWallUploadServerResponse = VkApiUtils.getWallUploadServer();
        String uploadLink = ApiHelper.getValueFromResponse(getWallUploadServerResponse.getBody().asString(), Keys.UPLOAD_URL);
        Response getFileToServerResponse = VkApiUtils.sendFileToServer(uploadLink, imageFullPath);
        String server = ApiHelper.getValueFromResponse(getFileToServerResponse.getBody().asString(), Keys.SERVER);
        String photo = TestHelper.removeSlashes(ApiHelper.getValueFromResponse(getFileToServerResponse.getBody().asString(), Keys.PHOTO));
        String hash = ApiHelper.getValueFromResponse(getFileToServerResponse.getBody().asString(), Keys.HASH);
        Response saveWallPhotoResponse = VkApiUtils.saveWallPhoto(server, photo, hash);
        String photoId = ApiHelper.getValueFromResponse(saveWallPhotoResponse.getBody().asString(), Keys.ID);
        photoId = TestHelper.removeAllButNumbers(photoId);
        return ApiHelper.makePhotoToSendToWallPost(ownerId, photoId);
    }

    public static String addCommentToWallPost(String ownerId, String postId, String text) {
        Response addCommentToWallPostResponse = VkApiUtils.addCommentToWallPost(ownerId, postId, text);
        return ApiHelper.getValueFromResponse(addCommentToWallPostResponse.getBody().asString(), Keys.COMMENT_ID);
    }
}
