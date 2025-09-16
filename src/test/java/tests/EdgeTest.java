package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeTest {

    @Test
    public void edge() {
    	System.setProperty("webdriver.edge.driver", "C:\\drivers\\edgedriver_win64\\msedgedriver.exe");

    	EdgeOptions options = new EdgeOptions();
    	options.addArguments("--inprivate");
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
