package com.selenium.practice.tests.checkoutflow;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.models.Product;
import com.selenium.practice.pages.CartPage;
import com.selenium.practice.pages.HomePage;

public class VerifyCartProducts extends AuthenticatedBaseTest {

    @Test
    public void checkCartItems() {

        HomePage homepage = new HomePage(driver);
        CartPage cartpage = new CartPage(driver);

        Product selectedProducts = homepage.addRandomProductToCart();

        logger.info("Selected Dashboard Product : Name = " + selectedProducts.getName() + ", Price = "
                + selectedProducts.getPrice());

        homepage.navigateCheckoutPage();

        List<Product> cartProduct = cartpage.getAllCheckoutProducts();

        boolean productFound = cartProduct.stream()
                .anyMatch(item -> item.getName().equals(selectedProducts.getName())
                        && Double.compare(item.getPrice(),
                                selectedProducts.getPrice()) == 0);

        Assert.assertTrue(productFound,
                "Selected product not found in cart: " + selectedProducts.getName());
    }

}
