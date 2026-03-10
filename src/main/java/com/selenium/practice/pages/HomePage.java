package com.selenium.practice.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.selenium.practice.enums.SortType;
import com.selenium.practice.models.Product;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By productContainer = By.className("inventory_item");
    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By sortContainer = By.cssSelector("[data-test='product-sort-container']");
    private By cartBtn = By.cssSelector(".btn_inventory");
    private By shoppingCartLink = By.cssSelector(".shopping_cart_link");
    private By shoppingCartBadge = By.cssSelector(".shopping_cart_badge");

    public List<Product> getAllProducts() {

        List<WebElement> products = driver.findElements(productContainer);
        List<Product> productList = new ArrayList<>();

        for (WebElement product : products) {
            String name = product.findElement(productName).getText();

            String priceText = product.findElement(productPrice).getText();

            double price = Double.parseDouble(priceText.replace("$", ""));
            productList.add(new Product(name, price));

        }
        return productList;
    }

    public void selectSortOption(SortType sortType) {

        Select select = new Select(driver.findElement(sortContainer));
        select.selectByVisibleText(sortType.getVisibleText());

    }

    public Product addRandomProductToCart() {

        List<WebElement> products = driver.findElements(productContainer);

        if (products.isEmpty()) {
            throw new RuntimeException("No products found on the page");
        }

        int randomIndex = new java.util.Random().nextInt(products.size());

        WebElement selectedProduct = products.get(randomIndex);

        String name = selectedProduct.findElement(productName).getText();

        String priceText = selectedProduct.findElement(productPrice).getText();
        double price = Double.parseDouble(priceText.replace("$", ""));

        selectedProduct.findElement(cartBtn).click();

        return new Product(name, price);
    }
    public List<Product> addRandomProductsToCart(int number) {

    List<WebElement> products = driver.findElements(productContainer);

    if (products.isEmpty()) {
        throw new RuntimeException("No products found on the page");
    }

    Collections.shuffle(products);

    List<Product> selectedProducts = new ArrayList<>();

    for (int i = 0; i < number; i++) {

        WebElement product = products.get(i);

        String name = product.findElement(productName).getText();

        String priceText = product.findElement(productPrice).getText();
        double price = Double.parseDouble(priceText.replace("$", ""));

        product.findElement(cartBtn).click();

        selectedProducts.add(new Product(name, price));
    }

    return selectedProducts;
}

    public int getCartBadgeCount(){

        if(driver.findElements(shoppingCartBadge).isEmpty()){
            return 0;
        }

        String badgeCount = driver.findElement(shoppingCartBadge).getText();
        return Integer.parseInt(badgeCount);

    }

}
