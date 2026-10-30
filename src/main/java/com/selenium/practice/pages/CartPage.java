package com.selenium.practice.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.practice.models.Product;
import com.selenium.practice.utils.WaitUtils;
import com.selenium.practice.utils.commonUtils;

public class CartPage {

    private final WebDriver driver;
    private final commonUtils utils;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.utils = new commonUtils(driver);
    }

    // Locator for the list of products shown in the cart/checkout page.
    private final By cartList = By.className("cart_list");

    // Locator for the product name in the checkout/cart page.
    private By checkoutAssetItemName = By.cssSelector("[data-test='inventory-item-name']");
    // Locator for the product price in the checkout/cart page.
    private By checkOutproductPrice = By.cssSelector("[data-test='inventory-item-price']");

    /**
     * Retrieves a list of products currently displayed on the checkout/cart page.
     * This uses dedicated locators in case the cart page structure differs from the
     * home page, though in this application they happen to be identical.
     *
     * @return list of {@link Product} objects representing items in the cart
     */
    public List<Product> getAllCheckoutProducts() {
        List<WebElement> products = driver.findElements(cartList);
        List<Product> checkoutProductList = new ArrayList<>();

        for (WebElement product : products) {
            String name = product.findElement(checkoutAssetItemName).getText();
            String priceText = product.findElement(checkOutproductPrice).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            checkoutProductList.add(new Product(name, price));
        }

        return checkoutProductList;
    }

    public String getCartProductName() {
        WebElement cartItemName = WaitUtils.waitForVisibility(driver, checkoutAssetItemName, 10);
        return cartItemName.getText().trim();
    }

    // Returns the product price displayed on the product details page.
    public double getCartProductPrice() {
        WebElement priceElement = WaitUtils.waitForVisibility(driver, checkOutproductPrice, 10);
        String priceText = priceElement.getText().trim();
        return Double.parseDouble(priceText.replace("$", ""));
    }

}
