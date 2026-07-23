package com.selenium.practice.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class for handling Explicit Waits in Selenium.
 */
public final class WaitUtils {

    // Prevent instantiation
    private WaitUtils() {
    }

    /**
     * Creates and returns a WebDriverWait instance.
     */
    private static WebDriverWait getWait(WebDriver driver, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /**
     * Wait until the element is visible.
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator, int seconds) {
        return getWait(driver, seconds)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait until the element is clickable.
     */
    public static WebElement waitForClickable(WebDriver driver, By locator, int seconds) {
        return getWait(driver, seconds)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait until the page URL contains the given text.
     */
    public static boolean waitForUrlContains(WebDriver driver, String text, int seconds) {
        return getWait(driver, seconds)
                .until(ExpectedConditions.urlContains(text));
    }
}