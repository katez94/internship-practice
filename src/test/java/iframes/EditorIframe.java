package iframes;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EditorIframe extends Form {
    private final ITextBox inputTextField = getElementFactory().getTextBox(By.xpath("//*[@id='tinymce']/p"), "text field");
    private final ILabel inputtedBoldText = getElementFactory().getLabel(By.xpath("//*[@id='tinymce']/p/strong"), "bold text");

    public EditorIframe() {
        super(By.id("mce_0_ifr"), "iframe");
    }

    private void switchToFrame() {
        AqualityServices.getBrowser().getDriver().switchTo().frame(this.getFormLabel().getElement());
    }

    private void switchBack() {
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }

    public void typeTextIntoTextField(String text) {
        switchToFrame();
        inputTextField.clearAndType(text);
        switchBack();
    }

    public String getInputtedText() {
        switchToFrame();
        String text = inputTextField.getText();
        switchBack();
        return text;
    }

    public Boolean isTextBold() {
        switchToFrame();
        boolean isTextBold = inputtedBoldText.getElement().getTagName().equals("strong");
        switchBack();
        return isTextBold;
    }
}
