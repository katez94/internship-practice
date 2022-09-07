package steps;

import aquality.selenium.browser.AqualityServices;
import forms.CardForm1;
import forms.CardForm2;
import utils.ScriptPathConsts;
import utils.TestHelper;
import java.awt.*;

public class Steps {

    public static void fillInCardForm1(CardForm1 card, String email, String password, String domain) {
        card.acceptCookies();
        card.clickSendToBottomBtn();
        card.inputRandomEmail(email);
        card.inputRandomPassword(password);
        card.inputRandomDomain(domain);
        card.openDropdownMenu();
        card.chooseOrgItemFromDropdownMenu();
        card.clickCheckboxToAcceptTerms();
    }

    public static void fillInCardForm2(CardForm2 card) throws AWTException {
        card.clickUploadBtn();
        TestHelper.uploadImage(TestHelper.getImagePathFromJson());
        AqualityServices.getBrowser().executeScript(ScriptPathConsts.SCROLL_TO_BOTTOM);
        card.unselectAllCheckboxes();
        card.choose3Interests();
    }
}
