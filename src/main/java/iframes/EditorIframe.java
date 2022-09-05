package iframes;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EditorIframe extends Form {

    private final ITextBox inputTextField = getElementFactory().getTextBox(By.xpath("//*[@id='tinymce']/p"), "text field");
    private final ILabel inputtedBoldText = getElementFactory().getLabel(By.xpath("//*[@id='tinymce']//strong"), "bold text");

    public EditorIframe() {
        super(By.id("mce_0_ifr"), "iframe");
    }

    public void typeTextIntoTextField(String text) {
        inputTextField.clearAndType(text);
    }

    public String getInputtedText() {
        return inputTextField.getText();
    }

    public Boolean isTextBold() {
        return inputtedBoldText.getElement().getTagName().equals("strong");
    }
}
