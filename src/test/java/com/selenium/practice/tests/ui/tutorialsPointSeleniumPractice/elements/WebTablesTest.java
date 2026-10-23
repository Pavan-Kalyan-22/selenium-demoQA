package com.selenium.practice.tests.ui.tutorialsPointSeleniumPractice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.ConfigReader;
import com.selenium.practice.utils.WaitUtils;

public class WebTablesTest extends BaseTest {

    @Test
    public void webtables() {

        String url = ConfigReader.getProperty("practice_url");
        driver.navigate().to(url);

        By elementOption = By.xpath("//button[text()=' Elements']");
        By webTablestext = By.xpath("//a[@href='webtables.php']");
        By addBtn = By.xpath("//button[normalize-space()='Add']");
        By Alden4deleteIcon = By.xpath("//tr[td[text()='Alden']][2]//a[contains(@class,'delete-wrap')]");

        WebElement element = WaitUtils.waitForVisibility(driver, elementOption, 10);
        driver.findElement(elementOption);
        element.click();

        WebElement webTablestextClick = WaitUtils.waitForClickable(driver, webTablestext, 10);
        driver.findElement(webTablestext);
        webTablestextClick.click();

        WebElement Alden4deleteIconClick = WaitUtils.waitForClickable(driver, Alden4deleteIcon, 10);
        driver.findElement(Alden4deleteIcon);
        Alden4deleteIconClick.click();

        driver.navigate().refresh();

        WebElement AddbtnClick = WaitUtils.waitForClickable(driver, addBtn, 10);
        driver.findElement(addBtn);
        AddbtnClick.click();

    }

}
