import org.testng.annotations.Test;

public class ApiTest {
    @Test
    public void test() {
        Steps.sendGetRequestToGetAllPosts();
        Steps.sendGetRequestToGetPostWithId99();
        Steps.sendGetRequestToGetPostWithId150();
        Steps.sendPostRequestToCreatePostWithUserId1AndRandomBodyAndTitle();
        Steps.sendGetRequestToGetAllUsers();
        Steps.sendGetRequestToGetUserWithId5();
    }
}
