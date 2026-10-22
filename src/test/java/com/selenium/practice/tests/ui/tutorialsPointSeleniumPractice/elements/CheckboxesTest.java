package com.selenium.practice.tests.ui.tutorialsPointSeleniumPractice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.ConfigReader;
import com.selenium.practice.utils.WaitUtils;

public class CheckboxesTest extends BaseTest {

    @Test
    public void checkboxPractice() {

        String url = ConfigReader.getProperty("practice_url");
        driver.navigate().to(url);
        By elementOption = By.xpath("//button[text()=' Elements']");
        By checkbox = By.xpath("//a[@href='check-box.php']");
        By checkbox1 = By.id("c_bs_1");
        By plus2 = By.xpath("//li[@id='bs_2']/span[@class='plus']");
        By plus4 = By.xpath("//li[@id='bf_4']/span[@class='plus']");

        WebElement element = WaitUtils.waitForVisibility(driver, elementOption, 10);
        driver.findElement(elementOption);
        element.click();

        WebElement clickCheckbox = WaitUtils.waitForClickable(driver, checkbox, 10);
        driver.findElement(checkbox);
        clickCheckbox.click();

        WebElement clickCheckbox1Element = WaitUtils.waitForClickable(driver, checkbox1, 10);
        driver.findElement(checkbox1);
        clickCheckbox1Element.click();

        WebElement clickPlus2Element = WaitUtils.waitForClickable(driver, plus2, 10);
        driver.findElement(plus2);
        clickPlus2Element.click();
       
        WebElement clickPlus4Element = WaitUtils.waitForClickable(driver, plus4, 10);
        driver.findElement(plus4);
        clickPlus4Element.click();
    }

}
