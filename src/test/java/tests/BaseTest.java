package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.elements.interfaces.IElementFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    Browser browser;
    IElementFactory elementFactory;

    @BeforeTest
    public void init(){
        browser = AqualityServices.getBrowser();
        elementFactory = AqualityServices.getElementFactory();
        browser.maximize();
    }

    @AfterTest
    public void quit(){
        browser.quit();
    }
}
