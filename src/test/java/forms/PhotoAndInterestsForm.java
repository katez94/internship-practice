package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class PhotoAndInterestsForm extends Form {
    private final String CHECKBOXES_LIST_LOCATOR = "//*[contains(@class,'checkbox__box')]";
    private final String CHECKBOXES_LABELS_LIST_LOCATOR = "//*[@class='checkbox small']/following-sibling::span[contains(text(),'')]";
    private final IButton nextBtn = getElementFactory().getButton(By.xpath("//*[@name='button' and contains(text(),'Next')]"), "next button");
    private final IButton uploadBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'avatar-and-interests__upload-button')]"), "upload btn");
    private final ICheckBox unselectAllCheckbox = getElementFactory().getCheckBox(By.xpath("//*[@id='interest_unselectall']/parent::label"), "unselect all checkbox");

    public PhotoAndInterestsForm() {
        super(By.xpath("//*[contains(@class,'page-indicator') and contains(text(),'2 / 4')]"), "interests form");
    }

    public void clickUploadBtn() {
        uploadBtn.click();
    }

    public void unselectAllCheckboxes() {
        unselectAllCheckbox.click();
    }

    public void clickNextBtn() {
        nextBtn.click();
    }

    public void clickInterestCheckbox(int index) {
        getInterestsList().get(index).getElement().click();
    }

    public int getSizeOfInterestsList() {
        return getInterestsList().size();
    }

    public List<ICheckBox> getInterestsList() {
        List<ICheckBox> listOfCheckboxes = getElementFactory().findElements(By.xpath(CHECKBOXES_LIST_LOCATOR), ElementType.CHECKBOX);
        List<ILabel> listOfCheckboxesLabels = getElementFactory().findElements(By.xpath(CHECKBOXES_LABELS_LIST_LOCATOR), ElementType.LABEL);
        List<ICheckBox> listWithoutSelectBtns = new ArrayList<>();
        for (ILabel label : listOfCheckboxesLabels) {
            if (!label.getText().contains("Select") && !label.getText().contains("Unselect")) {
                int indexOfNoSelectLabel = listOfCheckboxesLabels.indexOf(label);
                listWithoutSelectBtns.add(listOfCheckboxes.get(indexOfNoSelectLabel));
            }
        }
        return listWithoutSelectBtns;
    }
}
