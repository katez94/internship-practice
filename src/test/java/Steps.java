import client.Consts;
import client.JsonPlaceholderClient;
import com.google.gson.Gson;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import java.util.List;

public class Steps {
    public static List<Post> getAllPostsAsList() {
        Response response = JsonPlaceholderClient.sendGetRequest(Consts.ALL_POSTS);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        String responseBodyAsString = response.getBody().asString();
        return ApiTestHelper.getListOfPosts(responseBodyAsString);
    }

    public static Post getPostById(String id, int statusCode) {
        Response response = JsonPlaceholderClient.sendGetRequest(Consts.ALL_POSTS + id);
        Post post = ApiTestHelper.getPostModelFromResponse(response);
        Assert.assertEquals(response.getStatusCode(), statusCode);
        return post;
    }

    public static Response getPostByIdResponse(String id) {
        return JsonPlaceholderClient.sendGetRequest(id);
    }

    public static Response sendPost(Post post, int statusCode) {
        String postAsJson = new Gson().toJson(post);
        Response response = JsonPlaceholderClient.sendPostRequest(postAsJson, Consts.ALL_POSTS);
        Assert.assertEquals(response.getStatusCode(), statusCode);
        return response;
    }

    public static List<User> getAllUsersAsList() {
        Response response = JsonPlaceholderClient.sendGetRequest(Consts.ALL_USERS);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        String responseBodyAsString = response.getBody().asString();
        return ApiTestHelper.getListOfUsers(responseBodyAsString);
    }

    public static Response getUserById(int id, int statusCode) {
        Response response = JsonPlaceholderClient.sendGetRequest(Consts.ALL_USERS + id);
        Assert.assertEquals(response.getStatusCode(), statusCode);
        return response;
    }
}
