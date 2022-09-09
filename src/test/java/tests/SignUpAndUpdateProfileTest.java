package tests;

import forms.PersonalDetailsForm;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.Steps;
import utils.TestHelper;

import java.awt.*;

public class SignUpAndUpdateProfileTest extends BaseTest {
    private static final String EMAIL = RandomStringUtils.randomAlphabetic(5).toLowerCase();
    private static final String PASSWORD = TestHelper.generatePassword(EMAIL);
    private static final String DOMAIN = RandomStringUtils.randomAlphabetic(3).toLowerCase();

    @Test
    public void test() throws AWTException {
        Steps.navigateToMainPage();
        Steps.fillInRegistrationForm(EMAIL, PASSWORD, DOMAIN);
        Steps.fillInPhotoAndInterestsForm();
        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue(personalDetailsForm.state().isDisplayed());
    }
}
