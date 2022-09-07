package tests;

import aquality.selenium.browser.AqualityServices;
import forms.CardForm1;
import forms.CardForm2;
import forms.CardForm3;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import steps.Steps;
import utils.ScriptPathConsts;
import utils.TestHelper;
import java.awt.*;

public class SignUpAndUpdateProfileTest extends BaseTest {

    private static final String EMAIL = RandomStringUtils.randomAlphabetic(5).toLowerCase();
    private static final String PASSWORD = TestHelper.generatePassword(EMAIL);
    private static final String DOMAIN = RandomStringUtils.randomAlphabetic(3).toLowerCase();
    private static final String NUMBER_OF_FIRST_CARD = "1";
    private static final String NUMBER_OF_SECOND_CARD = "2";
    private static final String NUMBER_OF_THIRD_CARD = "3";

    @Test
    public void test() throws AWTException {
        AqualityServices.getLogger().info("Navigate to home page");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().isDisplayed());

        AqualityServices.getLogger().info("Click the link to navigate the next page");
        mainPage.clickLinkToTheNextPage();
        CardForm1 cardForm1 = new CardForm1();
        Assert.assertTrue(cardForm1.state().isDisplayed());
        Assert.assertTrue(cardForm1.checkCardNumber(NUMBER_OF_FIRST_CARD));

        AqualityServices.getLogger().info("Fill in the form with valid data, navigate to the next form");
        AqualityServices.getBrowser().executeScript(ScriptPathConsts.SCROLL_TO_TOP);
        Steps.fillInCardForm1(cardForm1, EMAIL, PASSWORD, DOMAIN);
        cardForm1.clickNextBtn();
        CardForm2 cardForm2 = new CardForm2();
        Assert.assertTrue(cardForm2.checkCardNumber(NUMBER_OF_SECOND_CARD));

        AqualityServices.getLogger().info("Update profile with photo and interests");
        Steps.fillInCardForm2(cardForm2);
        cardForm2.clickNextBtn();
        CardForm3 cardForm3 = new CardForm3();
        Assert.assertTrue(cardForm3.checkCardNumber(NUMBER_OF_THIRD_CARD));
    }
}
