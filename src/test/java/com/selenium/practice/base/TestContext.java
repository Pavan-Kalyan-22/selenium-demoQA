
package com.selenium.practice.base;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private final BaseTest baseTest;

    public TestContext() {
        baseTest = new BaseTest();
    }

    public void initializeBrowser() {
        baseTest.setUp();
    }

    public WebDriver getDriver() {
        return baseTest.getDriver();
    }

    public void closeBrowser() {
        baseTest.closeBrowser();
    }
}
