package pages;

import utils.ApiHelper;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.JavaScript;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.Attribute;

public class PersonalPage extends Form {
    private String wallPostLocator = "//*[@id='post%1$s_%2$s']";
    private String wallPostTextLocator = "//*[@id='post%1$s_%2$s']//*[contains(@class,'wall_post_text')]";
    private String wallPostPhotoLocator = "//*[@id='post%1$s_%2$s']//*[contains(@class,'page_post_sized_thumbs')]/a";
    private String commentAuthorLocator = "//*[@id='post%1$s_%2$s']//*[@class='author']";
    private String commentTextLocator = "//*[@id='wpt%1$s_%2$s']//*[contains(@class,'wall_reply_text')]";
    private String nextCommentBtnLocator = "//*[@id='post%1$s_%2$s']//*[contains(@class,'js-replies_next_label')]";
    private String likePathLocator = "//*[contains(@class,'like_wrap') and contains(@class,'_like_wall%1$s_%2$s')]//*[@data-section-ref='reactions-button-container']";

    public PersonalPage() {
        super(By.xpath("//*[@class='ProfileHeader__in']"), "personal header");
    }

    public void scrollToPost(String ownerId, String postId) {
        IComboBox post = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(wallPostLocator, ownerId, postId)), "wall post");
        AqualityServices.getBrowser().executeScript(JavaScript.SCROLL_TO_ELEMENT, post.getElement());
    }

    public String getTextFromWallPost(String postId) {
        ILabel postText = AqualityServices.getElementFactory().getLabel(By.xpath(String.format(wallPostTextLocator, ApiHelper.getValueFromJson(Attribute.OWNER_ID), postId)), "wall post text");
        return postText.getText();
    }

    public String getPhotoLink(String postId) {
        IComboBox photo = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(wallPostPhotoLocator, ApiHelper.getValueFromJson(Attribute.OWNER_ID), postId)), "photo");
        return photo.getElement().getAttribute("href");
    }

    public String getCommentAuthorId(String commentId) {
        IComboBox commentAuthor = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(commentAuthorLocator, ApiHelper.getValueFromJson(Attribute.OWNER_ID), commentId)), "comment author");
        return commentAuthor.getElement().getAttribute("data-from-id");
    }

    public String getTextFromComment(String authorId, String commentId) {
        ILabel commentText = AqualityServices.getElementFactory().getLabel(By.xpath(String.format(commentTextLocator, authorId, commentId)), "comment text");
        return commentText.getText();
    }

    public void clickToSeeTheComment(String authorId, String postId) {
        AqualityServices.getElementFactory().getLabel(By.xpath(String.format(nextCommentBtnLocator, authorId, postId)), "next comment button").click();
    }

    public void clickLikeBtn(String postId) {
        IButton likeBtn = AqualityServices.getElementFactory().getButton(By.xpath(String.format(likePathLocator, ApiHelper.getValueFromJson(Attribute.OWNER_ID), postId)), "like");
        likeBtn.click();
    }

    public Boolean isWallPostDeleted(String ownerId, String postId) {
        IComboBox post = AqualityServices.getElementFactory().getComboBox(By.xpath(String.format(wallPostLocator, ownerId, postId)), "wall post");
        return post.state().waitForExist();
    }
}
