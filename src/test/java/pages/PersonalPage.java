package pages;

import api.ApiHelper;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.JavaScript;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalPage extends Form {
    private final String postXpathBeginning = "//*[@id='post";
    private final String commentTextXpathBeginning = "//*[@id='wpt";
    private final String likeXpathBeginning = "//*[contains(@class,'like_wrap') and contains(@class,'_like_wall";
    private final String underscore = "_";
    private final String endPfXpath = "']";
    private final String wallPostTextPath = "//*[contains(@class,'wall_post_text')]";
    private final String photoPath = "//*[contains(@class,'page_post_sized_thumbs')]/a";
    private final String commentAuthorPath = "//*[@class='author']";
    private final String commentTextPath = "//*[contains(@class,'wall_reply_text')]";
    private final String nextCommentBtnPath = "//*[contains(@class,'js-replies_next_label')]";
    private final String likePath = "//*[@data-section-ref='reactions-button-container']";

    public PersonalPage() {
        super(By.xpath("//*[@class='ProfileHeader__in']"), "personal header");
    }

    public void scrollToPost(String ownerId, String postId) {
        IComboBox post = AqualityServices.getElementFactory().getComboBox(By.xpath(postXpathBeginning + ownerId + underscore + postId + endPfXpath), "post");
        AqualityServices.getBrowser().executeScript(JavaScript.SCROLL_TO_ELEMENT, post.getElement()); //todo script
    }

    public String getTextFromWallPost(String postId) {
        ILabel postText = AqualityServices.getElementFactory().getLabel(By.xpath(postXpathBeginning + ApiHelper.getOwnerId() + underscore + postId + endPfXpath + wallPostTextPath), "post text");
        return postText.getText();
    }

    public String getPhotoLink(String postId) {
        IComboBox photo = AqualityServices.getElementFactory().getComboBox(By.xpath(postXpathBeginning + ApiHelper.getOwnerId() + underscore + postId + endPfXpath + photoPath), "photo");
        return photo.getElement().getAttribute("href");
    }

    public String getCommentAuthorId(String commentId) {
        IComboBox commentAuthor = AqualityServices.getElementFactory().getComboBox(By.xpath(postXpathBeginning + ApiHelper.getOwnerId() + underscore + commentId + endPfXpath + commentAuthorPath), "comment author");
        return commentAuthor.getElement().getAttribute("data-from-id");
    }

    public String getTextFromComment(String authorId, String commentId) {
        ILabel commentText = AqualityServices.getElementFactory().getLabel(By.xpath(commentTextXpathBeginning + authorId + underscore + commentId + endPfXpath + commentTextPath), "comment text");
        return commentText.getText();
    }

    public void clickToSeeTheComment(String authorId, String postId) {
        AqualityServices.getElementFactory().getLabel(By.xpath(postXpathBeginning + authorId + underscore + postId + endPfXpath + nextCommentBtnPath), "").click();
    }

    public void clickLikeBtn(String postId) {
        IButton likeBtn = AqualityServices.getElementFactory().getButton(By.xpath(likeXpathBeginning + ApiHelper.getOwnerId() + "_" + postId + " ')]" + likePath), "like");
        likeBtn.click();
    }

    public Boolean isWallPostDeleted(String ownerId, String postId) {
        IComboBox post = AqualityServices.getElementFactory().getComboBox(By.xpath(postXpathBeginning + ownerId + underscore + postId + endPfXpath), "post");
        return post.state().waitForExist();
    }
}
