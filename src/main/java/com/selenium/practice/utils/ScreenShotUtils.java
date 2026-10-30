package com.selenium.practice.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtils {

    public static String captureScreenshot(
            WebDriver driver, String screenshotName) {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        Path destination = Paths.get(
                "screenshots", screenshotName + ".png");

        try {
            Files.createDirectories(destination.getParent());

            Files.copy(source.toPath(), destination);

            System.out.println("Screenshot saved at: "
                    + destination.toAbsolutePath());

            return destination.toString();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to capture screenshot", e);
        }
    }

}
