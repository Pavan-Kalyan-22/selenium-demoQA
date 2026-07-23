package com.selenium.practice.tests.ui.practiceinternethero;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.WaitUtils;

public class AddorRemoveElementTest extends BaseTest {

    @Test
    public void AddRemoveElementTest() {
        // Test methods for adding or removing elements will go here
        driver.navigate().to("https://the-internet.herokuapp.com/add_remove_elements/");

        By addElement = By.cssSelector("button[onclick='addElement()']");
        WaitUtils.waitForVisibility(driver, addElement, 10);
        driver.findElement(addElement).click();

    }

}
