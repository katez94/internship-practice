package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.ConfigProvider;

public class MainPage extends Form {
    Browser browser = AqualityServices.getBrowser();

    public MainPage() {
        super(By.className("start__paragraph"), "start__paragraph");
    }

    public void navigate() {
        browser.goTo(ConfigProvider.getInstance().getStartPageUrl());
    }

    private final ILink linkToTheNextPage = getElementFactory().getLink(By.className("start__link"), "start__link");

    public void clickLinkToTheNextPage() {
        linkToTheNextPage.click();
    }
}
