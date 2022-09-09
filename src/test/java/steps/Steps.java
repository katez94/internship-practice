package steps;

import aquality.selenium.browser.AqualityServices;
import forms.RegistrationForm;
import forms.PhotoAndInterestsForm;
import org.apache.commons.lang.math.RandomUtils;
import org.testng.Assert;
import pages.MainPage;
import utils.ScriptPathConsts;
import utils.TestHelper;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Steps {
    private static final MainPage MAIN_PAGE = new MainPage();
    private static final RegistrationForm REGISTRATION_FORM = new RegistrationForm();
    private static final PhotoAndInterestsForm PHOTO_AND_INTERESTS_FORM = new PhotoAndInterestsForm();

    public static void navigateToMainPage() {
        AqualityServices.getLogger().info("Navigate to home page");
        Assert.assertTrue(MAIN_PAGE.state().isDisplayed());
        AqualityServices.getLogger().info("Click the link to navigate the next page");
        MAIN_PAGE.clickLinkToTheNextPage();
    }

    public static void fillInRegistrationForm(String email, String password, String domain) {
        Assert.assertTrue(REGISTRATION_FORM.state().isDisplayed());
        AqualityServices.getLogger().info("Fill in the form with valid data, navigate to the next form");
        REGISTRATION_FORM.inputRandomEmail(email);
        REGISTRATION_FORM.inputRandomPassword(password);
        REGISTRATION_FORM.inputRandomDomain(domain);
        REGISTRATION_FORM.openDropdownMenu();
        REGISTRATION_FORM.chooseOrgItemFromDropdownMenu();
        REGISTRATION_FORM.clickCheckboxToAcceptTerms();
        REGISTRATION_FORM.clickNextBtn();
    }

    public static void fillInPhotoAndInterestsForm() throws AWTException {
        Assert.assertTrue(PHOTO_AND_INTERESTS_FORM.state().isDisplayed());
        AqualityServices.getLogger().info("Update profile with photo and interests");
        PHOTO_AND_INTERESTS_FORM.clickUploadBtn();
        TestHelper.uploadImage(TestHelper.getImagePathFromJson());
        AqualityServices.getBrowser().executeScript(ScriptPathConsts.SCROLL_TO_BOTTOM);
        PHOTO_AND_INTERESTS_FORM.unselectAllCheckboxes();
        choose3RandomInterests();
        PHOTO_AND_INTERESTS_FORM.clickNextBtn();
    }

    public static void choose3RandomInterests() {
        Set<Integer> setOfRandomIndexes = new HashSet<>();
        while (setOfRandomIndexes.size() < 3) {
            setOfRandomIndexes.add(RandomUtils.nextInt(PHOTO_AND_INTERESTS_FORM.getSizeOfInterestsList()));
        }
        for (Integer randomIndex : setOfRandomIndexes) {
            PHOTO_AND_INTERESTS_FORM.clickInterestCheckbox(randomIndex);
        }
    }

    public static void hideHelpForm() {
        AqualityServices.getLogger().info("Hide the help form");
        REGISTRATION_FORM.clickSendToBottomBtn();
        Assert.assertTrue(REGISTRATION_FORM.isHelpFormHidden());
    }

    public static void acceptCookies() {
        AqualityServices.getLogger().info("Accept cookies");
        REGISTRATION_FORM.acceptCookies();
        Assert.assertFalse(REGISTRATION_FORM.isCookiesFormOnScreen());
    }
}
