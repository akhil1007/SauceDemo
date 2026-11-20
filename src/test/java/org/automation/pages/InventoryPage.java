package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * SauceDemo product inventory page.
 */
public class InventoryPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

    /**
     * @param driver active WebDriver session
     */
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @return page header text
     */
    public String title() {
        return visible(TITLE).getText();
    }

    /**
     * Adds a product to the cart.
     *
     * @param productSlug product slug used in data-test ids, e.g. {@code sauce-labs-backpack}
     * @return this page
     */
    public InventoryPage addToCart(String productSlug) {
        click(By.cssSelector("[data-test='add-to-cart-" + productSlug + "']"));
        return this;
    }

    /**
     * @return item count shown on the cart badge
     */
    public int cartCount() {
        return Integer.parseInt(visible(CART_BADGE).getText());
    }
}

