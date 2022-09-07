package tests;

import aquality.selenium.browser.AqualityServices;
import forms.CardForm1;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

public class ValidateTimerStart extends BaseTest {
    private static final String NUMBER_OF_FIRST_CARD = "1";
    private static final String TIMER_START = "00:00:00";

    @Test
    public void test() {
        AqualityServices.getLogger().info("Navigate to home page");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().isDisplayed());

        AqualityServices.getLogger().info("Click the link to navigate the next page");
        mainPage.clickLinkToTheNextPage();
        CardForm1 cardForm1 = new CardForm1();
        Assert.assertTrue(cardForm1.state().isDisplayed());
        Assert.assertTrue(cardForm1.checkCardNumber(NUMBER_OF_FIRST_CARD));

        AqualityServices.getLogger().info("Validate timer start");
        Assert.assertEquals(cardForm1.getTextFromTimer(), TIMER_START);
    }
}
