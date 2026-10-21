package com.selenium.practice.tests.ui.tutorialsPointSeleniumPractice;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.utils.ConfigReader;

public class basicwebsiteLaunch extends BaseTest {

    @Test
    public void BasicLaunch() {

        String url = ConfigReader.getProperty("practice_url");

        driver.navigate().to(url);

        String Title = driver.getTitle();

        System.out.println("Title : " + Title);

        String getUrl = driver.getCurrentUrl();
        Assert.assertEquals(url, getUrl);
        System.out.println(getUrl);

        String getSource = driver.getPageSource();
        // System.out.println(getSource);

    }

}
