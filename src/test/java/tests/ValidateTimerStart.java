package tests;

import aquality.selenium.browser.AqualityServices;
import forms.RegistrationForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.Steps;

public class ValidateTimerStart extends BaseTest {
    private static final String TIMER_START = "00:00:00";

    @Test
    public void test() {
        Steps.navigateToMainPage();
        RegistrationForm registrationForm = new RegistrationForm();
        Assert.assertTrue(registrationForm.state().isDisplayed());
        AqualityServices.getLogger().info("Validate timer start");
        Assert.assertEquals(registrationForm.getTextFromTimer(), TIMER_START);
    }
}
