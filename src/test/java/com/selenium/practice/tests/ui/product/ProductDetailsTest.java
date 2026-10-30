package com.selenium.practice.tests.ui.product;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.models.Product;
import com.selenium.practice.pages.HomePage;

public class ProductDetailsTest extends AuthenticatedBaseTest {

    @Test
    public void verifyProductDetails() {

        HomePage homePage = new HomePage(driver);

        // Select a random product and navigate to details page
        Product dashboardProduct = homePage.selectRandomProductToDetailProduct();

        logger.info("Selected Dashboard Product: Name = "
                + dashboardProduct.getName() + ", Price = "
                + dashboardProduct.getPrice());

        // Retrieve product details
        Product detailsProduct = homePage.getProductDetails();

        logger.info("Details Page Product: Name = "
                + detailsProduct.getName() + ", Price = "
                + detailsProduct.getPrice());

        // Verify product details
        Assert.assertEquals(detailsProduct.getName(),
                dashboardProduct.getName(),
                "Product name does not match");

        Assert.assertEquals(detailsProduct.getPrice(),
                dashboardProduct.getPrice(),
                "Product price does not match");
    }
}