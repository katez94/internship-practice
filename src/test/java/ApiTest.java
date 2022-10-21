import aquality.selenium.browser.AqualityServices;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ApiTest {
    public static final String POST_ID_99 = "99";
    public static final String POST_ID_150 = "150";
    public static final Integer EXPECTED_USER_ID_10 = 10;
    public static final String RANDOM_BODY = RandomStringUtils.randomAlphabetic(10);
    public static final String RANDOM_TITLE = RandomStringUtils.randomAlphabetic(5);
    public static final Integer USER_ID_1 = 1;
    public static final Integer USER_ID_5 = 5;

    @Test
    public void test() {
        AqualityServices.getLogger().info("Get all posts as list -> status code 200, list is sorted ascending");
        List<Post> listOfPosts = Steps.getAllPostsAsList();
        List<Integer> listOfIdFromPosts = ApiTestHelper.getListOfIdFromPosts(listOfPosts);
        Assert.assertTrue(ApiTestHelper.isListOfIntegerSorted(listOfIdFromPosts));

        AqualityServices.getLogger().info("Get post with id=99 -> status code 200, userId=10, id=9, title and body aren't empty");
        Post postById99 = Steps.getPostById(POST_ID_99, HttpStatus.SC_OK);
        Assert.assertEquals(postById99.getId().toString(), POST_ID_99);
        Assert.assertEquals(postById99.getUserId(), EXPECTED_USER_ID_10);
        Assert.assertFalse(postById99.getTitle().isEmpty());
        Assert.assertFalse(postById99.getBody().isEmpty());

        AqualityServices.getLogger().info("Get post with id=150 -> status code 404, response body is empty");
        Steps.getPostById(POST_ID_150, HttpStatus.SC_NOT_FOUND);
        Response postById150Response = Steps.getPostByIdResponse(POST_ID_150);
        String bodyWithoutBraces = ApiTestHelper.removeBracesFromResponseBody(postById150Response);
        Assert.assertTrue(bodyWithoutBraces.isEmpty());

        AqualityServices.getLogger().info("Create post with userId=1, random body and title -> status code 201, post data matches data from request, id is present in response.");
        Post postToSend = ApiTestHelper.createPostModel(USER_ID_1, RANDOM_BODY, RANDOM_TITLE);
        Response sendPostResponse = Steps.sendPost(postToSend, HttpStatus.SC_CREATED);
        Post postFromRequest = ApiTestHelper.getPostModelFromResponse(sendPostResponse);
        Assert.assertEquals(postFromRequest.getTitle(), postToSend.getTitle());
        Assert.assertEquals(postFromRequest.getBody(), postToSend.getBody());
        Assert.assertEquals(postFromRequest.getUserId(), postToSend.getUserId());
        Assert.assertFalse(postFromRequest.getId().toString().isEmpty());

        AqualityServices.getLogger().info("Get all users as list -> status code 200, user(id=5) data matches data from file user5.json");
        List<User> allUsersAsList = Steps.getAllUsersAsList();
        User userById5 = ApiTestHelper.getUserFromListById(allUsersAsList, USER_ID_5);
        User userToCompare = ApiTestHelper.getUserFromJson(ApiTestHelper.getUserPathFromJson());
        Assert.assertEquals(userToCompare, userById5);

        AqualityServices.getLogger().info("Get user with id=5 -> status code 200, user data matches with user data in the previous step");
        Response userByIdResponse = Steps.getUserById(USER_ID_5, HttpStatus.SC_OK);
        User userFromRequest = ApiTestHelper.getUserModelFromResponse(userByIdResponse);
        Assert.assertEquals(userToCompare, userFromRequest);
    }
}
