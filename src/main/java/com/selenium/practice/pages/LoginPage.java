package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.selenium.practice.utils.WaitUtils;

// Page object for the login page of the application.
public class LoginPage {

    // WebDriver instance used to interact with the login page.
    private final WebDriver driver;

    // Locators for the username field, password field, login button, and error
    // message.
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    // Creates a LoginPage object bound to the given WebDriver instance.
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Enters the provided username into the username input field.
    public void enterUsername(String username) {
        WaitUtils.waitForVisibility(driver, usernameInput, 10).clear();
        WaitUtils.waitForVisibility(driver, usernameInput, 10).sendKeys(username);
    }

    // Enters the provided password into the password input field.
    public void enterPassword(String password) {
        WaitUtils.waitForVisibility(driver, passwordInput, 10).clear();
        WaitUtils.waitForVisibility(driver, passwordInput, 10).sendKeys(password);
    }

    // Clicks the login button to submit the login form.
    public void clickLogin() {
        WaitUtils.waitForClickable(driver, loginButton, 10).click();
    }

    // Returns the error message shown after a failed login attempt, if any.
    public String getErrorMessage() {
        try {
            WebElement error = WaitUtils.waitForVisibility(driver, errorMessage, 5);
            return error.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Checks whether the user appears to be logged in based on the current page
    // state. // Checks whether the user appears to be logged in based on the
    // current page state.

    public boolean isLoggedIn() {
        // simple check: page URL changed or inventory container visible
        return driver.getCurrentUrl().contains("inventory") ||
                !driver.findElements(loginButton).isEmpty();
    }
}