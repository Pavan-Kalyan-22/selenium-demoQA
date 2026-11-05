package com.selenium.practice.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selenium.practice.base.TestContext;
import com.selenium.practice.pages.LoginPage;

import io.cucumber.java.en.*;

public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private final TestContext context;
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    private void initializePage() {
        if (loginPage == null) {
            driver = context.getDriver();
            loginPage = new LoginPage(driver);
        }
    }

    @Given("user opens the login page")
    public void openLoginPage() {
        initializePage();
        logger.info("Opening login page and validating URL contains 'saucedemo.com'");
        Assert.assertTrue(
            driver.getCurrentUrl().contains("saucedemo.com"),
            "Login page was not opened"
        );
    }

    @When("user enters username {string}")
    public void enterUsername(String username) {
        logger.info("Entering username: [{}]", username);
        loginPage.enterUsername(username);
    }

    @When("user enters password {string}")
    public void enterPassword(String password) {
        logger.info("Entering password: [PROTECTED]");
        loginPage.enterPassword(password);
    }

    @When("user clicks the login button")
    public void clickLoginButton() {
        logger.info("Clicking the login button");
        loginPage.clickLogin();
    }

    @Then("user should verify login result {string}")
    public void verifyLoginResult(String result) {
        logger.info("Verifying login result: [{}] against current URL: [{}]", result, driver.getCurrentUrl());
        if (result.equalsIgnoreCase("success")) {
            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login was unsuccessful"
            );
        }
    }
}
