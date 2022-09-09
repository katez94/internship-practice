package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsForm extends Form {
    public PersonalDetailsForm() {
        super(By.xpath("//*[contains(@class,'page-indicator') and contains(text(),'3 / 4')]"), "personal details form");
    }
}
