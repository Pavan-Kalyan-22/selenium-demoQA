package com.selenium.practice.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenShotUtils.class);

    /**
     * Captures a screenshot from the provided WebDriver as a raw byte array.
     * Safely handles null drivers, non-screenshot-capable drivers, and runtime exceptions
     * so that screenshot failures never hide or suppress original test assertions.
     *
     * @param driver the active WebDriver instance
     * @return screenshot byte array, or null if capture failed
     */
    public static byte[] captureScreenshotAsBytes(WebDriver driver) {
        if (driver == null) {
            logger.warn("Cannot capture screenshot: WebDriver instance is null");
            return null;
        }

        try {
            if (driver instanceof TakesScreenshot) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                logger.warn("WebDriver instance does not implement TakesScreenshot: {}", driver.getClass().getName());
                return null;
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot as bytes: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Captures a screenshot and saves it as a PNG file in the screenshots/ directory.
     * Safely catches IO exceptions so that caller execution is not interrupted.
     *
     * @param driver the active WebDriver instance
     * @param screenshotName file name without extension
     * @return path to the saved screenshot, or null on failure
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            logger.warn("Cannot capture screenshot: WebDriver instance is null");
            return null;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            Path destination = Paths.get("screenshots", screenshotName + ".png");
            if (destination.getParent() != null) {
                Files.createDirectories(destination.getParent());
            }

            Files.copy(source.toPath(), destination);
            logger.info("Screenshot saved at: {}", destination.toAbsolutePath());
            return destination.toString();

        } catch (Exception e) {
            logger.error("Failed to capture screenshot file: {}", e.getMessage(), e);
            return null;
        }
    }
}
