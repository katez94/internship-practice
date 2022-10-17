import client.JsonPlaceholderClient;
import com.google.gson.Gson;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;

import java.util.List;

public class Steps {
    public static final String RANDOM_BODY = RandomStringUtils.randomAlphabetic(10);
    public static final String RANDOM_TITLE = RandomStringUtils.randomAlphabetic(5);
    public static final Integer POST_ID_99 = 99;
    public static final Integer POST_ID_150 = 150;
    public static final Integer USER_ID_1 = 1;
    public static final Integer USER_ID_5 = 5;
    public static final Integer EXPECTED_USER_ID_10 = 10;

    public static void sendGetRequestToGetAllPosts() {
        Response response = JsonPlaceholderClient.getGetAllPostsResponse();
        Assert.assertEquals(200, response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(ApiTestHelper.isStringJson(responseBodyAsString));
        List<Post> listOfPosts = ApiTestHelper.getListOfPosts(responseBodyAsString);
        List<Integer> listOfIdFromPosts = ApiTestHelper.getListOfIdFromPosts(listOfPosts);
        Assert.assertTrue(ApiTestHelper.isListOfIntegerSorted(listOfIdFromPosts));
    }

    public static void sendGetRequestToGetPostWithId99() {
        Response response = JsonPlaceholderClient.getPostByIdResponse(POST_ID_99);
        Post model = ApiTestHelper.getPostModel(response);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(model.getId(), POST_ID_99);
        Assert.assertEquals(model.getUserId(), EXPECTED_USER_ID_10);
        Assert.assertFalse(model.getTitle().isEmpty());
        Assert.assertFalse(model.getBody().isEmpty());
    }

    public static void sendGetRequestToGetPostWithId150() {
        Response response = JsonPlaceholderClient.getPostByIdResponse(POST_ID_150);
        Assert.assertEquals(response.getStatusCode(), 404);
        String bodyWithoutBraces = ApiTestHelper.removeBracesFromResponseBody(response);
        Assert.assertTrue(bodyWithoutBraces.isEmpty());
    }

    public static void sendPostRequestToCreatePostWithUserId1AndRandomBodyAndTitle() {
        Post postToAdd = ApiTestHelper.createPostModel(USER_ID_1, RANDOM_BODY, RANDOM_TITLE);
        String postJson = new Gson().toJson(postToAdd);
        Response response = JsonPlaceholderClient.getCreatePostResponse(postJson);
        Assert.assertEquals(response.getStatusCode(), 201);
        Post postFromRequest = response.getBody().as(Post.class);
        Assert.assertEquals(postFromRequest.getTitle(), postToAdd.getTitle());
        Assert.assertEquals(postFromRequest.getBody(), postToAdd.getBody());
        Assert.assertEquals(postFromRequest.getUserId(), postToAdd.getUserId());
    }

    public static void sendGetRequestToGetAllUsers() {
        Response response = JsonPlaceholderClient.getAllUsersResponse();
        Assert.assertEquals(response.getStatusCode(), 200);
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(ApiTestHelper.isStringJson(responseBodyAsString));
        List<User> listOfUsers = ApiTestHelper.getListOfUsers(responseBodyAsString);
        User userById5 = ApiTestHelper.getUserById(listOfUsers, USER_ID_5);
        User userToCompare = ApiTestHelper.getUserFromJson(ApiTestHelper.getUserPathFromJson());
        Assert.assertEquals(userToCompare, userById5);
    }

    public static void sendGetRequestToGetUserWithId5() {
        Response response = JsonPlaceholderClient.getUserByIdResponse(USER_ID_5);
        Assert.assertEquals(response.getStatusCode(), 200);
        User userFromRequest = response.getBody().as(User.class);
        User userToCompare = ApiTestHelper.getUserFromJson(ApiTestHelper.getUserPathFromJson());
        Assert.assertEquals(userToCompare, userFromRequest);
    }
}
