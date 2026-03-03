package com.selenium.practice.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.models.Product;
import com.selenium.practice.pages.HomePage;

public class ProductListTest extends AuthenticatedBaseTest {


     @Test
    public void verifyDefaultSortIsNameAtoZ() {

        logger.info("Starting test: verifyDefaultSortIsNameAtoZ");

        HomePage homePage = new HomePage(driver);

        logger.info("Fetching selected sort option");
        String selectedOption = homePage.getSelectedSortOption();

        logger.info("Selected sort option is: {}", selectedOption);

        Assert.assertEquals(selectedOption, "Name (A to Z)",
                "Default sort option is incorrect!");

        logger.info("Fetching all products");
        List<Product> products = homePage.getAllProducts();

        List<String> actualNames = products.stream()
                .map(Product::getName)
                .toList();

        logger.info("Actual product names: {}", actualNames);

        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        logger.info("Expected sorted names: {}", expectedNames);

        Assert.assertEquals(actualNames, expectedNames,
                "Products are NOT sorted A-Z by default!");

        logger.info("Test completed successfully");
    }}
