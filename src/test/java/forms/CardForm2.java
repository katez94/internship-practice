package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class CardForm2 extends Form {
    private final IButton nextBtn = getElementFactory().getButton(By.xpath("//*[@name='button' and contains(text(),'Next')]"), "next button");
    // todo получить лист чекбоксов
    private final List<ICheckBox> listOfCheckboxes = getElementFactory().findElements(By.xpath("//*[contains(@class,'checkbox__box')]/span"), ElementType.CHECKBOX);
    private final IButton uploadBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'avatar-and-interests__upload-button')]"), "upload btn");
    private final ICheckBox unselectAllCheckbox = getElementFactory().getCheckBox(By.xpath("//*[@id='interest_unselectall']/parent::label"), "unselect all checkbox");
    private final ICheckBox poniesInterest = getElementFactory().getCheckBox(By.xpath("//*[@for='interest_ponies']"), "ponies");
    private final ICheckBox snailsInterest = getElementFactory().getCheckBox(By.xpath("//*[@for='interest_snails']"), "snails");
    private final ICheckBox poloInterest = getElementFactory().getCheckBox(By.xpath("//*[@for='interest_polo']"), "polo");

    public CardForm2() {
        super(By.className("page-indicator"), "card form");
    }

    public Boolean checkCardNumber(String number) {
        return this.getFormLabel().getElement().getText().contains(number);
    }

    public void clickUploadBtn() {
        uploadBtn.click();
    }

    public void unselectAllCheckboxes() {
        unselectAllCheckbox.click();
    }

    public void choose3Interests() {
        poniesInterest.click();
        poloInterest.click();
        snailsInterest.click();
    }
    public void clickNextBtn() {
        nextBtn.click();
    }
}
