package steps;

import forms.PasswordForm;
import pages.SignInPage;
import utils.UiAttribute;

public class UIsteps {
    private static final SignInPage signInPage = new SignInPage();
    private static final PasswordForm passwordForm = new PasswordForm();

    public static void signIn() {
        signInPage.inputLogin(UiAttribute.getValueFromJson(UiAttribute.LOGIN));
        signInPage.clickSignInButton();
        passwordForm.inputPassword(UiAttribute.getValueFromJson(UiAttribute.PASSWORD));
        passwordForm.clickContinueButton();
    }
}
