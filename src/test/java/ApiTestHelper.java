import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.google.common.collect.Ordering;
import com.google.gson.Gson;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.json.TypeToken;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ApiTestHelper {

    public static boolean isStringJson(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            try {
                new JSONArray(json);
            } catch (JSONException ne) {
                return false;
            }
        }
        return true;
    }

    public static User getUserById(List<User> list, int id) {
        for (User user : list) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static User getUserFromJson(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            User user = new Gson().fromJson(reader, User.class);
            reader.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Post> getListOfPosts(String bodyAsString) {
        return new Gson().fromJson(bodyAsString, new TypeToken<List<Post>>() {
        }.getType());
    }

    public static List<User> getListOfUsers(String bodyAsString) {
        return new Gson().fromJson(bodyAsString, new TypeToken<List<User>>() {
        }.getType());
    }

    public static List<Integer> getListOfIdFromPosts(List<Post> listOfPosts) {
        List<Integer> listOfId = new ArrayList<>();
        for (Post post : listOfPosts) {
            listOfId.add(post.getId());
        }
        return listOfId;
    }

    public static Boolean isListOfIntegerSorted(List<Integer> list) {
        return Ordering.natural().isOrdered(list);
    }

    public static Post getPostModel(Response response) {
        return response.getBody().as(Post.class);
    }

    public static String removeBracesFromResponseBody(Response response) {
        String s = response.getBody().asString().replace("}", "");
        s = s.replace("{", "");
        return s;
    }

    public static Post createPostModel(int userId, String body, String title) {
        Post post = new Post();
        post.setUserId(userId);
        post.setBody(body);
        post.setTitle(title);
        return post;
    }

    public static String getUserPathFromJson() {
        ISettingsFile environment = new JsonSettingsFile("config.json");
        return environment.getValue("/user5Path").toString();
    }
}
