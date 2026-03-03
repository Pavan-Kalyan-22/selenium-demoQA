package com.selenium.practice.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.selenium.practice.models.Product;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private final By productContainer = By.className("inventory_item");
    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By sortContainer = By.cssSelector("[data-test='product-sort-container']");

public List<Product>getAllProducts(){

    List<WebElement>products = driver.findElements(productContainer);
    List<Product>productList = new ArrayList<>();

    for (WebElement product : products){
        String name = product.findElement(productName).getText();

        String priceText = product.findElement(productPrice).getText();

        double price = Double.parseDouble(priceText.replace("$", ""));
        productList.add(new Product(name,price));

    }
    return productList;
}


public String getSelectedSortOption(){
    Select select = new Select(driver.findElement(sortContainer));
    return  select.getFirstSelectedOption().getText();

}





}
