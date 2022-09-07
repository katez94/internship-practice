package forms;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CardForm1 extends Form {
    private final ITextBox passwordBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password box");
    private final ITextBox emailBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "email box");
    private final ITextBox domainBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain box");
    private final IButton dropdownOpener = getElementFactory().getButton(By.xpath("//*[contains(@class,'dropdown__opener')]"), "dropdown opener");
    private final ILabel dropdownOrgItem = getElementFactory().getLabel(By.xpath("//*[contains(@class,'dropdown__list-item') and contains(text(),'.org')]"), "item");
    private final IComboBox cookiesMessage = getElementFactory().getComboBox(By.xpath("//*[@class='cookies__message']"), "cookies message");
    private final IButton acceptCookiesBtn = getElementFactory().getButton(By.xpath("//*[@name='button' and contains(text(),'no')]"), "cookies button");
    private final ILabel helpFormTitle = getElementFactory().getLabel(By.xpath("//*[contains(@class,'help-form__title')]"), "help form title");
    private final IButton sendToBottomBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'button') and contains(@class,'button--solid') and contains(@class,'button--blue') and contains(@class,'help-form__send-to-bottom-button')]"), "sendToBottom button");
    private final ICheckBox termsCheckbox = getElementFactory().getCheckBox(By.xpath("//*[contains(@class,'checkbox__label')]"), "terms checkbox");
    private final IButton nextBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'button--secondary')]"), "next button");
    private final ILabel timerText = getElementFactory().getLabel(By.xpath("//*[contains(@class,'timer') and contains(@class,'timer--center')]"), "timer text");

    public CardForm1() {
        super(By.className("page-indicator"), "card form");
    }

    public Boolean checkCardNumber(String number) {
        return this.getFormLabel().getElement().getText().contains(number);
    }

    public void inputRandomPassword(String password) {
        passwordBox.clearAndType(password);
    }

    public void inputRandomEmail(String email) {
        emailBox.clearAndType(email);
    }

    public void inputRandomDomain(String domain) {
        domainBox.clearAndType(domain);
    }

    public void openDropdownMenu() {
        dropdownOpener.click();
    }

    public void chooseOrgItemFromDropdownMenu() {
        //todo click on any item from list
        dropdownOrgItem.click();
    }

    public void acceptCookies() {
        acceptCookiesBtn.click();
    }

    public void clickSendToBottomBtn() {
        sendToBottomBtn.click();
    }

    public void clickCheckboxToAcceptTerms() {
        termsCheckbox.click();
    }

    public void clickNextBtn() {
        nextBtn.click();
    }

    public Boolean isHelpFormHidden() {
        return helpFormTitle.state().waitForNotDisplayed();
    }

    public Boolean isCookiesFormOnScreen() {
        return cookiesMessage.state().isDisplayed();
    }

    public String getTextFromTimer() {
        return timerText.getText();
    }
}
