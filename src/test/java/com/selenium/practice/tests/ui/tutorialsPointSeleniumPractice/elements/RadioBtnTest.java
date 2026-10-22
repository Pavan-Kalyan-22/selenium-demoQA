package com.selenium.practice.tests.ui.tutorialsPointSeleniumPractice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.ConfigReader;
import com.selenium.practice.utils.WaitUtils;

public class RadioBtnTest extends BaseTest {

    @Test
    public void radioTest() {

        String url = ConfigReader.getProperty("practice_url");
        driver.navigate().to(url);

        By radioBtn = By.xpath("//a[@href='radio-button.php']");
        By yesradioBtn = By.xpath("//input[@type='radio' and @value='igottwo']");
        By labelText = By.cssSelector("#check");
        By elementOption = By.xpath("//button[text()=' Elements']");

        WebElement element = WaitUtils.waitForVisibility(driver, elementOption, 10);
        driver.findElement(elementOption);
        element.click();

        WebElement radioBtntxtClick = WaitUtils.waitForClickable(driver, radioBtn, 10);
        driver.findElement(radioBtn);
        radioBtntxtClick.click();

        WebElement yesRadioClick = WaitUtils.waitForClickable(driver, yesradioBtn, 10);
        driver.findElement(yesradioBtn);
        yesRadioClick.click();

        WebElement yesLabel = WaitUtils.waitForVisibility(driver, labelText, 10);
        driver.findElement(labelText);

        System.out.println(yesLabel.getText());

    }

}
