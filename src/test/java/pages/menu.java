package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.commonUtils;

public class menu {

    private WebDriver driver;
    private commonUtils utils;

    public menu(WebDriver driver) {
        this.driver = driver;
        this.utils = new commonUtils(driver);
    }

    // Locator
    private By elementsCard = By.xpath("//h5[text()='Elements']");

    // Action
    public void tapElements() {
        utils.scrollToElement(elementsCard);
        utils.click(elementsCard);
    }
}
