package com.selenium.practice.tests.checkoutflow;

import java.util.List;

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
        int cartCount = homePage.getCartBadgeCount();
logger.info(cartCount);

        Assert.assertEquals(cartCount, 1,
                "Cart count did not increase after adding product");

        logger.info("Selected Product: "
                + selectedProduct.getName()
                + " | Price: "
                + selectedProduct.getPrice());

        // navigate and verify the same product appears in the cart
        homePage.navigateCheckoutPage();
        List<Product> checkoutProducts = homePage.getAllCheckoutProducts();

        Assert.assertEquals(checkoutProducts.size(), 1,
                "Expected exactly one product in the checkout page");
        Product productInCart = checkoutProducts.get(0);

        // compare using equals (name + price) to ensure exact match
        Assert.assertEquals(productInCart, selectedProduct,
                "Product details in cart do not match the selected product");

                logger.info("Cart List : " + productInCart);
    }
}