package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CardForm3 extends Form {

    public CardForm3() {
        super(By.className("page-indicator"), "card form");
    }

    public Boolean checkCardNumber(String number) {
        return this.getFormLabel().getElement().getText().contains(number);
    }
}
