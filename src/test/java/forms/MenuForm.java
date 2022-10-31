package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MenuForm extends Form {
    private final IButton personalPageButton = AqualityServices.getElementFactory().getButton(By.xpath("//*[@id='l_pr']"), "personal page button");

    public MenuForm() {
        super(By.xpath("//*[@id='side_bar_inner']"), "menu");
    }

    public void goToPersonalPage() {
        personalPageButton.click();
    }
}
