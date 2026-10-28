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

        // Select a random product from dashboard and navigate to the details page
        Product dashboardProduct = homePage.selectRandomProductToDetailProduct();

        logger.info("Selected Dashboard Product: Name = " + dashboardProduct.getName()
                + ", Price = " + dashboardProduct.getPrice());

        // Retrieve product name and price from the product details page
        String detailsPageName = homePage.getDetailsPageProductName();
        double detailsPagePrice = homePage.getDetailsPageProductPrice();

        logger.info("Details Page Product: Name = " + detailsPageName
                + ", Price = " + detailsPagePrice);

        // Compare the Product name from dashboard and in details page
        Assert.assertEquals(detailsPageName, dashboardProduct.getName(),
                "Product name on details page does not match dashboard product name");

        // Compare the Product price from dashboard and in details page
        Assert.assertEquals(detailsPagePrice, dashboardProduct.getPrice(),
                "Product price on details page does not match dashboard product price");

        // Verify the Product model matches completely (name and price)
        Product detailsProduct = homePage.getProductDetails();
        Assert.assertEquals(detailsProduct, dashboardProduct,
                "Product details object (name and price) does not match the dashboard product");
    }

}
