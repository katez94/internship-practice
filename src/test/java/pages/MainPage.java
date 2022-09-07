package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private final ILink linkToTheNextPage = getElementFactory().getLink(By.className("start__link"), "start__link");

    public MainPage() {
        super(By.className("start__paragraph"), "start__paragraph");
    }

    public void clickLinkToTheNextPage() {
        linkToTheNextPage.click();
    }
}
