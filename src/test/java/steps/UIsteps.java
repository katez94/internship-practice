package steps;

import forms.PasswordForm;
import pages.SignInPage;
import utils.UIHelper;

public class UIsteps {
    private static final SignInPage signInPage = new SignInPage();
    private static final PasswordForm passwordForm = new PasswordForm();

    public static void signIn() {
        signInPage.inputLogin(UIHelper.getLogin());
        signInPage.clickSignInButton();
        passwordForm.inputPassword(UIHelper.getPassword());
        passwordForm.clickContinueButton();
    }
}
