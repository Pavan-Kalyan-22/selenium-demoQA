package com.selenium.practice.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.selenium.practice.utils.ConfigReader;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (DriverManager.class) {
                if (driver == null) {
                    String browser = ConfigReader.getProperty("browser").toLowerCase();
                    switch (browser) {
                        case "chrome":
                            WebDriverManager.chromedriver().setup();
                            driver = new ChromeDriver();
                            break;
                        case "edge":
                        default:
                            System.setProperty("webdriver.edge.driver",
            ConfigReader.getProperty("edge.path"));
            driver = new EdgeDriver();
                            break;
                    }
                    driver.manage().window().maximize();
                }
            }
        }
        return driver;
    }

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
