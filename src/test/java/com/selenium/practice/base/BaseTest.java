package com.selenium.practice.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.selenium.practice.utils.ConfigReader;

// Base class for shared test setup and cleanup.
public class BaseTest {

    // WebDriver instance used by the test methods in this class.
    protected WebDriver driver;

    // Logger for recording test setup and execution details.
    protected Logger logger = LogManager.getLogger(this.getClass());

    // Sets up the browser and opens the application URL before the test class runs.
    @BeforeClass
    public void setUp() {
        // Set up the ChromeDriver automatically.
        WebDriverManager.chromedriver().setup();

        // Configure Chrome to start in a maximized and incognito window.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        // Create the ChromeDriver with the configured options.
        driver = new ChromeDriver(options);

        // Read the application URL from configuration properties.
        String url = ConfigReader.getProperty("url");

        // Open the target URL in the browser.
        driver.get(url);
    }

    // Closes the browser session after the test class finishes.
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
