import aquality.selenium.browser.AqualityServices;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;
import steps.Steps;

public class IframeTests extends BaseTest {
    private final static String PAGE_TITLE = "An iFrame containing the TinyMCE WYSIWYG Editor";
    private final static String GENERATED_RANDOM_TEXT = RandomStringUtils.randomAlphabetic(10);

    @Test
    public void iframeTest() {
        AqualityServices.getLogger().info("Step 1 - open resource page");
        Steps.navigateToEditorPage(PAGE_TITLE);

        AqualityServices.getLogger().info("Step 2 - clear text box and input generated random text");
        Steps.inputTextIntoTextField(GENERATED_RANDOM_TEXT);

        AqualityServices.getLogger().info("Step 3 - highlight inputted text and click B button");
        Steps.makeInputtedTextBold();
    }
}
