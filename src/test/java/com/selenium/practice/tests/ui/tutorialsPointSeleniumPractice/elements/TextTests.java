package com.selenium.practice.tests.ui.tutorialsPointSeleniumPractice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.ConfigReader;
import com.selenium.practice.utils.WaitUtils;

public class TextTests extends BaseTest {

    @Test
    public void TestText() {

        String url = ConfigReader.getProperty("practice_url");
        driver.navigate().to(url);

        By elementOption = By.xpath("//button[text()=' Elements']");
        By fullName = By.id("fullname");
        By text = By.xpath("//a[@href='text-box.php']");
        By Email = By.id("email");
        By address = By.id("address");
        By password = By.id("password");
        By submit = By.xpath("//input[@type='submit']");

        WebElement element = WaitUtils.waitForVisibility(driver, elementOption, 10);
        driver.findElement(elementOption);
        element.click();

        WebElement textClick = WaitUtils.waitForClickable(driver, text, 10);
        driver.findElement(text);
        textClick.click();

        WebElement fullNameclick = WaitUtils.waitForClickable(driver, fullName, 10);
        driver.findElement(fullName);
        fullNameclick.sendKeys("Round 1");

        WebElement Emailclick = WaitUtils.waitForClickable(driver, Email, 10);
        driver.findElement(Email);
        Emailclick.sendKeys("name@example.com");

        WebElement addressclick = WaitUtils.waitForClickable(driver, address, 10);
        driver.findElement(address);
        addressclick.sendKeys("Address");

        WebElement passwordClick = WaitUtils.waitForClickable(driver, password, 10);
        driver.findElement(password);
        passwordClick.sendKeys("12345");

        WebElement submitClick = WaitUtils.waitForClickable(driver, submit, 10);
        driver.findElement(password);
        submitClick.click();

    }
}
