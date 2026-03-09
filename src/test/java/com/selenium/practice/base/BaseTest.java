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

public class BaseTest {

    protected WebDriver driver;

        protected Logger logger = LogManager.getLogger(this.getClass());


    @BeforeClass
    public void setUp() {
        // Setup Edge browser
       WebDriverManager.chromedriver().setup();
       
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--start-maximized");
       options.addArguments("--incognito");

driver = new ChromeDriver(options);
        // Get URL from config
        String url = ConfigReader.getProperty("url");
        
        driver.get(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
