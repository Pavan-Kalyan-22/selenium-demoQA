package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Elements;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

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
