
package com.selenium.practice.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selenium.practice.base.TestContext;
import com.selenium.practice.pages.LoginPage;

import io.cucumber.java.en.*;

public class LoginSteps {

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

        Assert.assertTrue(
            driver.getCurrentUrl().contains("saucedemo.com"),
            "Login page was not opened"
        );
    }

    @When("user enters username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("user enters password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("user clicks the login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @Then("user should verify login result {string}")
    public void verifyLoginResult(String result) {

        if (result.equalsIgnoreCase("success")) {

            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login was unsuccessful"
            );
        }
    }
}
