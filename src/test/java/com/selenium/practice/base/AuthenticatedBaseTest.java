package com.selenium.practice.base;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import com.selenium.practice.pages.LoginPage;
import com.selenium.practice.utils.ConfigReader;
import com.selenium.practice.utils.WaitUtils;

public class AuthenticatedBaseTest extends BaseTest {


    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();  // Driver setup from parent class
        performLogin(); // Login after driver is ready
    }

    /**
     * Performs login with valid credentials from config.properties
     * Called automatically before each test method
     */
    protected void performLogin() {
        LoginPage loginPage = new LoginPage(driver);

        String username = ConfigReader.getProperty("standard.user");
        String password = ConfigReader.getProperty("standard.password");

        // Perform login steps
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Wait for login to complete - verify hamburger menu is visible
    //     WaitUtils.waitForClickable(driver, By.id("react-burger-menu-btn"), 10);
     }
}
