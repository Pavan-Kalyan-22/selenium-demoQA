package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elements {
	
WebDriver driver;



private By element = By.name("Elements");


public Elements(WebDriver driver) {
	this.driver = driver;
	
}
public void tapElement() {
   driver.findElement(element).click();
}

}
