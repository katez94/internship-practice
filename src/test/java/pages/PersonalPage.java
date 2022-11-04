package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.JavaScript;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.ApiAttribute;

public class PersonalPage extends Form {
    private static final String WALL_POST_LOCATOR = "//*[@id='post%1$s_%2$s']";
    private static final String WALL_POST_TEXT_LOCATOR = "//*[@id='post%1$s_%2$s']//*[contains(@class,'wall_post_text')]";
    private static final String WALL_POST_PHOTO_LOCATOR = "//*[@id='post%1$s_%2$s']//*[contains(@class,'page_post_sized_thumbs')]/a";
    private static final String COMMENT_AUTHOR_LOCATOR = "//*[@id='post%1$s_%2$s']//*[@class='author']";
    private static final String COMMENT_TEXT_LOCATOR = "//*[@id='wpt%1$s_%2$s']//*[contains(@class,'wall_reply_text')]";
    private static final String NEXT_COMMENT_BTN_LOCATOR = "//*[@id='post%1$s_%2$s']//*[contains(@class,'js-replies_next_label')]";
    private static final String LIKE_PATH_LOCATOR = "//*[contains(@class,'like_wrap') and contains(@class,'_like_wall%1$s_%2$s')]//*[@data-section-ref='reactions-button-container']";

    public PersonalPage() {
        super(By.xpath("//*[@class='ProfileHeader__in']"), "personal header");
    }

    public void scrollToPost(String ownerId, String postId) {
        IComboBox post = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(WALL_POST_LOCATOR, ownerId, postId)), "wall post");
        AqualityServices.getBrowser().executeScript(JavaScript.SCROLL_TO_ELEMENT, post.getElement());
    }

    public String getTextFromWallPost(String postId) {
        ILabel postText = AqualityServices.getElementFactory().getLabel(By.xpath(String.format(WALL_POST_TEXT_LOCATOR, ApiAttribute.getValueFromJson(ApiAttribute.OWNER_ID), postId)), "wall post text");
        return postText.getText();
    }

    public String getPhotoLink(String postId) {
        IComboBox photo = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(WALL_POST_PHOTO_LOCATOR, ApiAttribute.getValueFromJson(ApiAttribute.OWNER_ID), postId)), "photo");
        return photo.getElement().getAttribute("href");
    }

    public String getCommentAuthorId(String commentId) {
        IComboBox commentAuthor = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(COMMENT_AUTHOR_LOCATOR, ApiAttribute.getValueFromJson(ApiAttribute.OWNER_ID), commentId)), "comment author");
        return commentAuthor.getElement().getAttribute("data-from-id");
    }

    public String getTextFromComment(String authorId, String commentId) {
        ILabel commentText = AqualityServices.getElementFactory().getLabel(By.xpath(String.format(COMMENT_TEXT_LOCATOR, authorId, commentId)), "comment text");
        return commentText.getText();
    }

    public void clickToSeeTheComment(String authorId, String postId) {
        AqualityServices.getElementFactory().getLabel(By.xpath(String.format(NEXT_COMMENT_BTN_LOCATOR, authorId, postId)), "next comment button").click();
    }

    public void clickLikeBtn(String postId) {
        IButton likeBtn = AqualityServices.getElementFactory().getButton(By.xpath(String.format(LIKE_PATH_LOCATOR, ApiAttribute.getValueFromJson(ApiAttribute.OWNER_ID), postId)), "like");
        likeBtn.click();
    }

    public Boolean isWallPostDeleted(String ownerId, String postId) {
        IComboBox post = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(WALL_POST_LOCATOR, ownerId, postId)), "wall post");
        return post.state().waitForExist();
    }
}
