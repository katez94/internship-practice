package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SignInPage extends Form {
    private final ITextBox loginBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//*[@id='index_email']"), "login");
    private final IButton signInBtn = AqualityServices.getElementFactory().getButton(By.xpath("//*[contains(@class,'FlatButton__content') and contains(text(),'Sign in')]"), "login");

    public SignInPage() {
        super(By.xpath("//*[contains(@class,'VkIdForm__header')]"), "sign in header");
    }

    public void inputLogin(String login) {
        loginBox.clearAndType(login);
    }

    public void clickSignInButton() {
        signInBtn.click();
    }
}
