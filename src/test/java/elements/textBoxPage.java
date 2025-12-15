package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.commonUtils;

public class textBoxPage {

    private commonUtils utils;

    public textBoxPage(WebDriver driver) {
        this.utils = new commonUtils(driver);
    }

    // Locators
    private By textBox = By.xpath("//span[text()='Text Box']");
    private By fullName = By.id("userName");

    // Actions
    public void tapTextBox() {
  
        utils.scrollToElement(textBox);
        utils.click(textBox);
    }

    public void enterFullName(String name) {
        utils.sendKeys(fullName, name);
    }
}
