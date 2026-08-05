package com.selenium.practice.tests.ui.practiceinternethero;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.WaitUtils;

public class DragandDropTest extends BaseTest {

    @Test
    public void DragandDrop() {

        By draganddrop = By.cssSelector("a[href='/drag_and_drop']");
        By columnA = By.id("column-a");
        By columnB = By.id("column-b");

        driver.navigate().to("https://the-internet.herokuapp.com");

        WaitUtils.waitForVisibility(driver, draganddrop, 10);
        driver.findElement(draganddrop).click();

        WaitUtils.waitForUrlContains(driver, "drag_and_drop", 10);

        WebElement source = WaitUtils.waitForVisibility(driver, columnA, 10);
        WebElement target = WaitUtils.waitForVisibility(driver, columnB, 10);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

    }

}
