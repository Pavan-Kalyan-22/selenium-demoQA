package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Elements;

public class EdgeTest extends BaseTest{

    @Test
    public void edge() {
    	Elements elements = new Elements(driver);

    	elements.tapElement();
    	
    }
}
