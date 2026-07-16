package com.selenium.practice.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.selenium.practice.utils.ConfigReader;

// Manages the creation and lifecycle of a shared WebDriver instance.
public class DriverManager {

    // Stores the single shared WebDriver instance used across the test session.
    private static WebDriver driver;

    // Prevents direct instantiation of this utility class.
    private DriverManager() {
    }

    // Creates and returns a shared WebDriver instance if one does not already exist.
    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (DriverManager.class) {
                if (driver == null) {
                    // Read the browser name from configuration and start the matching browser.
                    String browser = ConfigReader.getProperty("browser").toLowerCase();
                    switch (browser) {
                        case "chrome":
                            // Set up ChromeDriver automatically using WebDriverManager.
                            WebDriverManager.chromedriver().setup();
                            driver = new ChromeDriver();
                            break;
                        case "edge":
                        default:
                            // Configure the EdgeDriver path from properties and initialize Edge.
                            System.setProperty("webdriver.edge.driver",
            ConfigReader.getProperty("edge.path"));
            driver = new EdgeDriver();
                            break;
                    }
                    // Maximize the browser window after initialization.
                    driver.manage().window().maximize();
                }
            }
        }
        return driver;
    }

    // Closes the current browser session and clears the stored driver reference.
    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
