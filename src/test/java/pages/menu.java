package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.selenium.practice.utils.*;

public class menu {

    private WebDriver driver;
    private commonUtils utils;

    public menu(WebDriver driver) {
        this.driver = driver;
        this.utils = new commonUtils(driver);  
    }

    private By element = By.xpath("//h5[text()='Elements']");

    private By textBox = By.xpath("//span[text()='Text Box']");
    
    
    public void tapElement() {
        WebElement ele = driver.findElement(element);

        utils.scrollToElement(ele);  
        utils.click(ele);            
    }
    
    public void tapTextBox() {
    	
    	WebElement txtBox = driver.findElement(textBox);
    	utils.click(txtBox);
    	
    }
}
