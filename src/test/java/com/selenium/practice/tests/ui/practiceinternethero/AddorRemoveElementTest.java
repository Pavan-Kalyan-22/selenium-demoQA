package com.selenium.practice.tests.ui.practiceinternethero;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.WaitUtils;

public class AddorRemoveElementTest extends BaseTest {

    @Test
    public void AddRemoveElementTest() {
        // Test methods for adding or removing elements will go here
        driver.navigate().to("https://the-internet.herokuapp.com/");

        By label = By.cssSelector("a[href='/add_remove_elements/']");

        WaitUtils.waitForVisibility(driver, label, 10);

        driver.findElement(label).click();

        By addElement = By.cssSelector("button[onclick='addElement()']");
        WaitUtils.waitForVisibility(driver, addElement, 10);

        int click = 0;

        for (int i = 0; i <= 5; i++) {
            driver.findElement(addElement).click();
            click++;

        }

        

    }

}
