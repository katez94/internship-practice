package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import utils.TestHelper;

import java.util.List;
import java.util.Locale;

public class CardForm extends Form {

    public CardForm() {
        super(By.className("page-indicator"), "card form");
    }

    private final ILabel pageIndicator = getElementFactory().getLabel(By.className("page-indicator"), "page-indicator");
    private final ITextBox passwordBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password box");
    private final ITextBox emailBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "email box");
    private final ITextBox domainBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain box");
    private final IButton dropdownOpener = getElementFactory().getButton(By.className("dropdown__opener"), "dropdown opener");
    private final ILabel dropdownOrgItem = getElementFactory().getLabel(By.xpath("//*[@class='dropdown__list-item' and contains(text(),'.org')]"), "item");
    private final IComboBox cookiesMessage = getElementFactory().getComboBox(By.xpath("//*[@class='cookies__message']"), "cookies message");
    private final IButton acceptCookiesBtn = getElementFactory().getButton(By.xpath("//*[@name='button' and contains(text(),'no')]"), "cookies button");
    private final IComboBox helpForm = getElementFactory().getComboBox(By.xpath("//*[@class='help-form' or contains(@class,'is-hidden')]"), "help form");
    private final IButton sendToBottomBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'button') and contains(@class,'button--solid') and contains(@class,'button--blue') and contains(@class,'help-form__send-to-bottom-button')]"), "sendToBottom button");
    private final ICheckBox termsCheckbox = getElementFactory().getCheckBox(By.xpath("//*[@class='checkbox__label']"), "terms checkbox");
    private final IButton nextBtn = getElementFactory().getButton(By.xpath("//*[@class='button--secondary']"), "next button");
    private final IButton nextBtnTo3rdForm = getElementFactory().getButton(By.xpath("//*[@name='button' and contains(text(),'Next')]"), "next button");

    // todo получить лист чекбоксов
    private final List<ICheckBox> listOfCheckboxes = getElementFactory().findElements(By.xpath("//*[@class='checkbox__box']/span"), ElementType.CHECKBOX);
    private final IButton uploadBtn = getElementFactory().getButton(By.xpath("//*[@class='avatar-and-interests__upload-button']"), "upload btn");
    private final ICheckBox unselectAllCheckbox = getElementFactory().getCheckBox(By.xpath("//*[@id='interest_unselectall']/parent::label"), "unselect all checkbox");
    private final ICheckBox poniesInterest = getElementFactory().getCheckBox(By.xpath("//*[@for='interest_ponies']"), "ponies");
    private final ICheckBox snailsInterest = getElementFactory().getCheckBox(By.xpath("//*[@for='interest_snails']"), "snails");
    private final ICheckBox poloInterest = getElementFactory().getCheckBox(By.xpath("//*[@for='interest_polo']"), "polo");

    private final ILabel timerText = getElementFactory().getLabel(By.xpath("//*[contains(@class,'timer') and contains(@class,'timer--center')]"), "timer text");

    public Boolean checkCardNumber(String number) {
        return pageIndicator.getText().contains(number);
    }

    private static final String email = RandomStringUtils.randomAlphabetic(5).toLowerCase();

    public void inputRandomPassword() {
        String oneLetterFromEmail = email.substring(0, 1);
        char cyrillycLetter = TestHelper.createCyrillicSymbol();
        passwordBox.clearAndType(RandomStringUtils.randomAlphabetic(7) + RandomStringUtils.randomNumeric(1) + cyrillycLetter + oneLetterFromEmail);
    }

    public void inputRandomEmail() {
        emailBox.clearAndType(email);
    }

    public void inputRandomDomain(Integer length) {
        domainBox.clearAndType(RandomStringUtils.randomAlphabetic(length).toLowerCase(Locale.ROOT));
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

    public void clickUploadBtn() {
        uploadBtn.click();
    }

    public void clickNextBtnToForm3() {
        nextBtnTo3rdForm.click();
    }

    public void unselectAllCheckboxes() {
        unselectAllCheckbox.click();
    }

    public void choose3Interests() {
        poniesInterest.click();
        poloInterest.click();
        snailsInterest.click();
    }

    public Boolean checkIfHelpFormIsHidden() {
        return helpForm.getAttribute("class").contains("hidden");
    }

    public Boolean checkIfCookiesFormIsOnScreen() {
        return cookiesMessage.state().isDisplayed();
    }

    public String getTextFromTimer() {
        return timerText.getText();
    }
}
