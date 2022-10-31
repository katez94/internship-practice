package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordForm extends Form {
    private final ITextBox passwordBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//*[@type='password']"), "password box");
    private final IButton continueBtn = AqualityServices.getElementFactory().getButton(By.xpath("//*[@class='vkuiButton__in']"), "continue button");

    public PasswordForm() {
        super(By.xpath("//*[@data-key='login']"), "login");
    }

    public void inputPassword(String password) {
        passwordBox.clearAndType(password);
    }

    public void clickContinueButton() {
        continueBtn.click();
    }
}
