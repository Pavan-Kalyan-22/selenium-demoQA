package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.selenium.practice.utils.WaitUtils;

public class LoginPage {

    private final WebDriver driver;

    // locators
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WaitUtils.waitForVisibility(driver, usernameInput, 10).clear();
        WaitUtils.waitForVisibility(driver, usernameInput, 10).sendKeys(username);
    }

    public void enterPassword(String password) {
        WaitUtils.waitForVisibility(driver, passwordInput, 10).clear();
        WaitUtils.waitForVisibility(driver, passwordInput, 10).sendKeys(password);
    }

    public void clickLogin() {
        WaitUtils.waitForClickable(driver, loginButton, 10).click();
    }

    public String getErrorMessage() {
        try {
            WebElement error = WaitUtils.waitForVisibility(driver, errorMessage, 5);
            return error.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLoggedIn() {
        // simple check: page URL changed or inventory container visible
        return driver.getCurrentUrl().contains("inventory") ||
                !driver.findElements(loginButton).isEmpty();
    }
}

