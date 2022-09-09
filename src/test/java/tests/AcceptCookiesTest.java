package tests;

import org.testng.annotations.Test;
import steps.Steps;

public class AcceptCookiesTest extends BaseTest {
    @Test
    public void test() {
        Steps.navigateToMainPage();
        Steps.acceptCookies();
    }
}
