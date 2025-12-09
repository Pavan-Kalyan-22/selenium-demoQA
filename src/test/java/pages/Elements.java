package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.commonUtils;

public class Elements {

    private WebDriver driver;
    private commonUtils utils;

    public Elements(WebDriver driver) {
        this.driver = driver;
        this.utils = new commonUtils(driver);  
    }

    private By element = By.xpath("//h5[text()='Elements']");

    public void tapElement() {
        WebElement ele = driver.findElement(element);

        utils.scrollToElement(ele);  
        utils.click(ele);            
    }
}
