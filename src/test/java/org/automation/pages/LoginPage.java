package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * SauceDemo login page.
 */
public class LoginPage extends BasePage {

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    /**
     * @param driver active WebDriver session
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the login page.
     *
     * @param baseUrl application base URL
     * @return this page
     */
    public LoginPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    /**
     * Submits credentials.
     *
     * @param username login user
     * @param password login password
     * @return inventory page (valid only on successful login)
     */
    public InventoryPage loginAs(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
        return new InventoryPage(driver);
    }

    /**
     * @return displayed login error text
     */
    public String errorMessage() {
        return visible(ERROR).getText();
    }
}

