import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    Browser browser;
    ISettingsFile environment = new JsonSettingsFile("config.json");
    String url = environment.getValue("/url").toString();

    @BeforeTest
    public void init() {
        System.out.println(url);
        browser = AqualityServices.getBrowser();
        browser.goTo(url);
        browser.maximize();
    }

    @AfterTest
    public void quit() {
        browser.quit();
    }
}

