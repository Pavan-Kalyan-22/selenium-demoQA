package com.selenium.practice.tests.checkoutflow;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.pages.HomePage;

@Listeners({ com.selenium.practice.listeners.TestExecutionListener.class })

public class RemoveProductFromCartTest extends AuthenticatedBaseTest {

        @Test
        public void removeProductFromCart() {
                HomePage homePage = new HomePage(driver);

                homePage.addRandomProductsToCart(1);
                homePage.navigateCheckoutPage();

                int initialBadgeCount = homePage.getCartBadgeCount();
                Assert.assertEquals(initialBadgeCount, 1,
                                "Expected one product in cart before removal");

                homePage.removeProduct();

                int finalBadgeCount = homePage.getCartBadgeCount();
                Assert.assertEquals(finalBadgeCount, 0,
                                "Cart badge should be empty after removal");

        }

}
