import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.UiAttribute;

public class BaseTest {
    @BeforeTest
    public void init() {
        AqualityServices.getBrowser().goTo(UiAttribute.getValueFromJson(UiAttribute.URL));
        AqualityServices.getBrowser().maximize();
    }

    @AfterTest
    public void quit() {
        AqualityServices.getBrowser().quit();
    }
}
