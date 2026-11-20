package org.automation.tests;

import static org.testng.Assert.assertEquals;

import org.automation.pages.InventoryPage;
import org.testng.annotations.Test;

/**
 * Cart scenarios.
 */
public class CartTest extends BaseTest {

    @Test(groups = "smoke")
    public void addToCart_whenTwoProductsAdded_thenBadgeShowsTwo() {
        InventoryPage inventory = openLogin().loginAs(STANDARD_USER, PASSWORD)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light");
        assertEquals(inventory.cartCount(), 2);
    }
}

