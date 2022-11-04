import utils.ApiHelper;
import api.Keys;
import api.Types;
import aquality.selenium.browser.AqualityServices;
import forms.MenuForm;
import io.restassured.response.Response;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PersonalPage;
import steps.ApiSteps;
import steps.UIsteps;
import utils.ApiAttribute;

import java.io.File;

public class VkTest extends BaseTest {
    public static final String OWNER_ID = ApiAttribute.getValueFromJson(ApiAttribute.OWNER_ID);
    public static final String RANDOM_TEXT = RandomStringUtils.randomAlphabetic(10);
    public static final String RANDOM_TEXT_FOR_EDIT = RandomStringUtils.randomAlphabetic(10);
    public static final String RANDOM_COMMENT = RandomStringUtils.randomAlphabetic(10);
    public static final String POSITIVE_LIKE_STATUS = "1";
    public static final String IMAGE_FULL_PATH = new File(ApiAttribute.getValueFromJson(ApiAttribute.IMAGE_PATH)).getAbsolutePath();

    @Test
    public void test() {
        AqualityServices.getLogger().info("Авторизоваться");
        UIsteps.signIn();

        AqualityServices.getLogger().info("Перейти на Мою страницу");
        MenuForm menu = new MenuForm();
        menu.goToPersonalPage();

        AqualityServices.getLogger().info("Создать запись со случайно сгенерированным текстом на стене и получить id записи");
        Response response = ApiSteps.createPostOnTheWall(OWNER_ID, RANDOM_TEXT);
        String postId = ApiHelper.getValueFromResponse(response.getBody().asString(), Keys.POST_ID);

        AqualityServices.getLogger().info("Не обновляя страницу убедиться, что на стене появилась запись с нужным текстом от правильного пользователя");
        PersonalPage personalPage = new PersonalPage();
        personalPage.scrollToPost(OWNER_ID, postId);
        String textFromPost = personalPage.getTextFromWallPost(postId);
        Assert.assertEquals(textFromPost, RANDOM_TEXT);

        AqualityServices.getLogger().info("Отредактировать запись через запрос к API - изменить текст и добавить (загрузить) любую картинку");
        String photoId = ApiSteps.getPhotoId(OWNER_ID, IMAGE_FULL_PATH);
        ApiSteps.editPostOnTheWall(OWNER_ID, postId, RANDOM_TEXT_FOR_EDIT, photoId);

        AqualityServices.getLogger().info("Не обновляя страницу убедиться, что изменился текст сообщения и добавилась загруженная картинка (картинки одинаковые)");
        String photoLink = personalPage.getPhotoLink(postId);
        Assert.assertTrue(photoLink.contains(photoId));
        String textAfterEdit = personalPage.getTextFromWallPost(postId);
        Assert.assertNotEquals(textFromPost, textAfterEdit);

        AqualityServices.getLogger().info("Используя запрос к API добавить комментарий к записи со случайным текстом");
        String commentId = ApiSteps.addCommentToWallPost(OWNER_ID, postId, RANDOM_COMMENT);

        AqualityServices.getLogger().info("Не обновляя страницу убедиться, что к нужной записи добавился комментарий от правильного пользователя");
        personalPage.clickToSeeTheComment(OWNER_ID, postId);
        String commentAuthorId = personalPage.getCommentAuthorId(commentId);
        Assert.assertEquals(commentAuthorId, OWNER_ID);
        String textFromComment = personalPage.getTextFromComment(commentAuthorId, commentId);
        Assert.assertEquals(textFromComment, RANDOM_COMMENT);

        AqualityServices.getLogger().info("Через UI оставить лайк к записи");
        personalPage.clickLikeBtn(postId);

        AqualityServices.getLogger().info("Через запрос к API убедиться, что у записи появился лайк от правильного пользователя");
        Response isItemLikedResponse = ApiSteps.isItemLiked(OWNER_ID, postId, Types.POST);
        System.out.println(isItemLikedResponse.getBody().asString());
        String likeStatus = ApiHelper.getValueFromResponse(isItemLikedResponse.getBody().asString(), Keys.LIKED);
        Assert.assertEquals(likeStatus, POSITIVE_LIKE_STATUS);

        AqualityServices.getLogger().info("Через запрос к API удалить созданную запись");
        ApiSteps.deleteWallPost(OWNER_ID, postId);

        AqualityServices.getLogger().info("Не обновляя страницу убедиться, что запись удалена");
        Boolean isWallPostDeleted = personalPage.isWallPostDeleted(OWNER_ID, postId);
        Assert.assertTrue(isWallPostDeleted);
    }
}
