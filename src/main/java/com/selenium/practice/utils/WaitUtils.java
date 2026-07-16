package com.selenium.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Utility class for waiting on common Selenium conditions.
public class WaitUtils {

    // Prevents accidental instantiation of this utility class.
    private WaitUtils() {
        // utility class
    }

    // Waits until the element located by the given locator is visible.
    public static WebElement waitForVisibility(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Waits until the element located by the given locator is clickable.
    public static WebElement waitForClickable(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Waits until the current page URL contains the provided text.
    public static boolean waitForUrlContains(WebDriver driver, String text, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.urlContains(text));
    }
}
