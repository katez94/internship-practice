import aquality.selenium.browser.AqualityServices;
import iframes.EditorIframe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.EditorPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestHelper;

public class IframeTests extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(IframeTests.class);
    private final static String PAGE_TITLE = "An iFrame containing the TinyMCE WYSIWYG Editor";
    private final static String GENERATED_RANDOM_TEXT = TestHelper.generateRandomText(10);

    @Test
    public void iframeTest() {
        logger.info("Step 1 - open resource page");
        EditorPage editorPage = new EditorPage();
        Assert.assertTrue(editorPage.state().isDisplayed());
        Assert.assertEquals(editorPage.getTitleText(), PAGE_TITLE);

        logger.info("Step 2 - clear text box and input generated random text");
        editorPage.switchToFrame();
        EditorIframe iframe = new EditorIframe();
        iframe.typeTextIntoTextField(GENERATED_RANDOM_TEXT);
        Assert.assertEquals(iframe.getInputtedText(), GENERATED_RANDOM_TEXT);

        logger.info("Step 3 - highlight inputted text and click B button");
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
        editorPage.openEditMenu();
        editorPage.selectInputtedText();
        editorPage.clickBoldBtn();
        editorPage.switchToFrame();
        Assert.assertTrue(iframe.isTextBold());
    }
}
