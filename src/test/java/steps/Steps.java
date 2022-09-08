package steps;

import iframes.EditorIframe;
import org.testng.Assert;
import pages.EditorPage;

public class Steps {

    private static final EditorPage editorPage = new EditorPage();
    private static final EditorIframe iframe = new EditorIframe();

    public static void navigateToEditorPage(String pageTitle){
        Assert.assertTrue(editorPage.state().isDisplayed());
        Assert.assertEquals(editorPage.getTitleText(), pageTitle);
    }

    public static void makeInputtedTextBold(){
        editorPage.openEditMenu();
        editorPage.selectInputtedText();
        editorPage.clickBoldBtn();
        Assert.assertTrue(iframe.isTextBold());
    }

    public static void inputTextIntoTextField(String randomText){
        iframe.typeTextIntoTextField(randomText);
        Assert.assertEquals(iframe.getInputtedText(), randomText);
    }
}
