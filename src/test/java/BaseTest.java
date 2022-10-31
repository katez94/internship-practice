import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.UIHelper;

public class BaseTest {
    @BeforeTest
    public void init() {
        AqualityServices.getBrowser().goTo(UIHelper.getUrl());
        AqualityServices.getBrowser().maximize();
    }

    @AfterTest
    public void quit() {
        AqualityServices.getBrowser().quit();
    }
}
