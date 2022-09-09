package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    private final ISettingsFile environment = new JsonSettingsFile("config.json");
    private final String url = environment.getValue("/startPageUrl").toString();

    @BeforeTest
    public void init() {
        AqualityServices.getBrowser().goTo(url);
        AqualityServices.getBrowser().maximize();
    }

    @AfterTest
    public void quit() {
        AqualityServices.getBrowser().quit();
    }
}
