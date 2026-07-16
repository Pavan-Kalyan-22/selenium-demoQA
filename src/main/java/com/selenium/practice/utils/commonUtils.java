package com.selenium.practice.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

// Utility class for common browser actions such as scrolling and clicking elements.
public class commonUtils {

    // WebDriver instance used by the utility methods.
    WebDriver driver;

    // Creates a utility instance tied to the given WebDriver.
    public commonUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Scrolls the given web element into view at the center of the viewport.
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // Waits until the element is clickable and then clicks it.
    public void click(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
