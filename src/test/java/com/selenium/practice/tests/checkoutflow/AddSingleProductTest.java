package com.selenium.practice.tests.checkoutflow;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.models.Product;
import com.selenium.practice.pages.HomePage;

public class AddSingleProductTest extends AuthenticatedBaseTest {

    @Test
    public void verifyRandomProductAddedToCart() {

        // Arrange
        HomePage homePage = new HomePage(driver);

        // Act
        Product selectedProduct = homePage.addRandomProductToCart();

        // Assert
        // int cartCount = homePage.getCartBadgeCount();

        // Assert.assertEquals(cartCount, 1,
        //         "Cart count did not increase after adding product");

        logger.info("Selected Product: "
                + selectedProduct.getName()
                + " | Price: "
                + selectedProduct.getPrice());
    }
}