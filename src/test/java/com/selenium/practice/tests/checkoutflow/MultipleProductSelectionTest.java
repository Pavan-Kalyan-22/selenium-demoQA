package com.selenium.practice.tests.checkoutflow;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.models.Product;
import com.selenium.practice.pages.HomePage;

public class MultipleProductSelectionTest extends AuthenticatedBaseTest {


  @Test
public void verifyMultipleProductTest(){

    HomePage homePage = new HomePage(driver);

    List<Product> selectedProducts = homePage.addRandomProductsToCart(4);

    logger.info("Selected Products" + selectedProducts);

    int badgeCount = homePage.getCartBadgeCount();

    logger.info("Cart Badge Count " + badgeCount);

    Assert.assertEquals(badgeCount, selectedProducts.size(),
            "Cart badge count mismatch");
}
}
