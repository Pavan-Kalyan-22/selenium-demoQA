package com.selenium.practice.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.selenium.practice.base.TestContext;
import com.selenium.practice.pages.CartPage;
import com.selenium.practice.pages.HomePage;

import io.cucumber.java.en.*;

public class AddSingleProductSteps {

    private static final Logger logger = LogManager.getLogger(AddSingleProductSteps.class);
    private final TestContext context;
    private WebDriver driver;
    private HomePage homePage;
    private CartPage cartPage;

    public AddSingleProductSteps(TestContext context) {
        this.context = context;
    }

    private void initializePages() {
        if (homePage == null) {
            driver = context.getDriver();
            homePage = new HomePage(driver);
            cartPage = new CartPage(driver);
        }
    }

    @When("user selects a product")
    public void selectProduct() {
        initializePages();
        logger.info("Selecting random product from dashboard and adding to cart");
        homePage.addRandomProductToCart();
    }

    @When("user navigates to cart page")
    public void navigate() {
        logger.info("Navigating to shopping cart page");
        homePage.navigateCheckoutPage();
    }

    @Then("user verifies the selected product in cart")
    public void check() {
        logger.info("Retrieving cart item list to verify selected product");
        cartPage.getAllCheckoutProducts();
    }
}
