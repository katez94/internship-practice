package tests;

import forms.CardForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

public class TestCase2 extends BaseTest{

    @Test
    public void test(){
        //step 1
        MainPage mainPage = new MainPage();
        mainPage.navigate();
        Assert.assertTrue(mainPage.state().isDisplayed());

        //step 2
        mainPage.clickLinkToTheNextPage();
        CardForm cardForm = new CardForm();
        Assert.assertTrue(cardForm.state().isDisplayed());
        Assert.assertTrue(cardForm.checkCardNumber("1"));

        //step 3
        cardForm.clickSendToBottomBtn();
        Assert.assertTrue(cardForm.checkIfHelpFormIsHidden());
    }
}
