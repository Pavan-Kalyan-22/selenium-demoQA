package com.selenium.practice.tests.ui.login;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.pages.LoginPage;
import com.selenium.practice.utils.ConfigReader;
import com.selenium.practice.utils.WaitUtils;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                // user, password, shouldSucceed, expectedErrorFragment
                {ConfigReader.getProperty("standard.user"), ConfigReader.getProperty("standard.password"), true, ""},
                {ConfigReader.getProperty("standard.user"), ConfigReader.getProperty("invalid.password"), false, "Username and password do not match"},
                {ConfigReader.getProperty("locked.user"), ConfigReader.getProperty("standard.password"), false, "locked out"},
                {"", ConfigReader.getProperty("standard.password"), false, "Username is required"},
                {ConfigReader.getProperty("standard.user"), "", false, "Password is required"},
                {"", "", false, "Username is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginScenarios(String user, String pass, boolean shouldSucceed, String expectedErrorFragment) {
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.clickLogin();

        if (shouldSucceed) {
            // wait for inventory page or other indicator
            Assert.assertTrue(loginPage.isLoggedIn(), "Expected to be logged in but was not");
        } else {
            String msg = loginPage.getErrorMessage();
            Assert.assertTrue(msg.toLowerCase().contains(expectedErrorFragment.toLowerCase()),
                    "Error message did not contain expected text. Actual='" + msg + "'");
        }
    }


}

