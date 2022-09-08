package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EditorPage extends Form {
    private final ILabel pageTitleText = AqualityServices.getElementFactory().getLabel(By.xpath("//*[contains(@class,'example')]/h3"), "title");
    private final IButton editMenuBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'tox-mbtn__select-label') and contains(text(),'Edit')]"), "edit menu");
    private final IButton selectAllBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'tox-collection__item-label') and contains(text(),'Select all')]"), "select all item");
    private final IButton boldBtn = getElementFactory().getButton(By.xpath("//*[contains(@class,'tox-tbtn') and contains(@title,'Bold')]"), "bold button");

    public EditorPage() {
        super(By.className("example"), "editor");
    }

    public String getTitleText() {
        return pageTitleText.getText();
    }

    public void openEditMenu() {
        editMenuBtn.click();
    }

    public void selectInputtedText() {
        selectAllBtn.click();
    }

    public void clickBoldBtn() {
        boldBtn.click();
    }
}
