package org.automation.tests;

import org.automation.config.DriverFactory;
import org.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Per-test browser lifecycle.
 */
public abstract class BaseTest {

    protected static final String BASE_URL = System.getProperty("baseUrl", "https://www.saucedemo.com/");
    protected static final String STANDARD_USER = "standard_user";
    protected static final String PASSWORD = "secret_sauce";

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.create();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected LoginPage openLogin() {
        return new LoginPage(driver).open(BASE_URL);
    }
}

