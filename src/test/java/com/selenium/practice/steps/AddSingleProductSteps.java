
package com.selenium.practice.steps;

import org.openqa.selenium.WebDriver;

import com.selenium.practice.base.TestContext;
import com.selenium.practice.pages.CartPage;
import com.selenium.practice.pages.HomePage;

import io.cucumber.java.en.*;

public class AddSingleProductSteps {

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
        homePage.addRandomProductToCart();
    }

    @When("user navigates to cart page")
    public void navigate() {
        homePage.navigateCheckoutPage();
    }

    @Then("user verifies the selected product in cart")
    public void check() {
        cartPage.getAllCheckoutProducts();
    }
}
