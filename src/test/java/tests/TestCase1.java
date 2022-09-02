package tests;

import forms.CardForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.ConfigProvider;
import utils.ScriptPathConsts;
import utils.TestHelper;

import java.awt.*;
public class TestCase1 extends BaseTest {

    @Test
    public void test() throws AWTException {
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
        browser.executeScript(ScriptPathConsts.SCROLL_TO_TOP);
        cardForm.acceptCookies();
        cardForm.clickSendToBottomBtn();
        cardForm.inputRandomEmail();
        cardForm.inputRandomPassword();
        cardForm.inputRandomDomain(3);
        cardForm.openDropdownMenu();
        cardForm.chooseOrgItemFromDropdownMenu();
        cardForm.clickCheckboxToAcceptTerms();
        cardForm.clickNextBtn();
        Assert.assertTrue(cardForm.checkCardNumber("2"));

        //step 4
        cardForm.clickUploadBtn();
        TestHelper.uploadImage(ConfigProvider.getInstance().getImageFullPath());
        browser.executeScript(ScriptPathConsts.SCROLL_TO_BOTTOM);
        cardForm.unselectAllCheckboxes();
        cardForm.choose3Interests();
        cardForm.clickNextBtnToForm3();
        Assert.assertTrue(cardForm.checkCardNumber("3"));
    }
}
