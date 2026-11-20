package org.automation.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.automation.pages.LoginPage;
import org.testng.annotations.Test;

/**
 * Login scenarios.
 */
public class LoginTest extends BaseTest {

    @Test(groups = "smoke")
    public void login_whenValidCredentials_thenProductsPageShown() {
        String title = openLogin().loginAs(STANDARD_USER, PASSWORD).title();
        assertEquals(title, "Products");
    }

    @Test(groups = "regression")
    public void login_whenLockedOutUser_thenLockedOutErrorShown() {
        LoginPage page = openLogin();
        page.loginAs("locked_out_user", PASSWORD);
        assertTrue(page.errorMessage().contains("locked out"), "Expected locked-out error");
    }

    @Test(groups = "regression")
    public void login_whenWrongPassword_thenMismatchErrorShown() {
        LoginPage page = openLogin();
        page.loginAs(STANDARD_USER, "wrong_password");
        assertTrue(page.errorMessage().contains("do not match"), "Expected credential mismatch error");
    }
}

