package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class commonUtils {

    private WebDriver driver;
    private WaitUtils wait;

    public commonUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // Scroll to element
    public void scrollToElement(By locator) {
        WebElement element = wait.waitForVisibility(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // Click safely
    public void click(By locator) {
        WebElement element = wait.waitForClickability(locator);
        element.click();
    }

    // Send keys safely
    public void sendKeys(By locator, String text) {
        WebElement element = wait.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }
}
