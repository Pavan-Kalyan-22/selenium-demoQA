package com.selenium.practice.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.selenium.practice.enums.SortType;
import com.selenium.practice.models.Product;

// Page object representing the home page of the application.
public class HomePage {

    // WebDriver instance used to interact with the page.
    private final WebDriver driver;

    // Creates a HomePage object tied to the provided WebDriver instance.
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for each product card on the home page.
    private final By productContainer = By.className("inventory_item");
    // Locator for the list of products shown in the cart/checkout page.
    private final By cartList = By.className("cart_list");
    // Locator for the product name within a product card.
    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    // Locator for the product price within a product card.
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    // Locator for the sorting dropdown on the home page.
    private By sortContainer = By.cssSelector("[data-test='product-sort-container']");
    // Locator for the product name in the checkout/cart page.
    private By checkoutAssetItemName = By.cssSelector("[data-test='inventory-item-name']");
    // Locator for the product price in the checkout/cart page.
    private By checkOutproductPrice = By.cssSelector("[data-test='inventory-item-price']");

    // Locator for the Add to Cart button on a product card.
    private By cartBtn = By.cssSelector(".btn_inventory");
    // Locator for the shopping cart link in the header.
    private By shoppingCartLink = By.cssSelector(".shopping_cart_link");
    // Locator for the badge showing the number of items in the cart.
    private By shoppingCartBadge = By.cssSelector(".shopping_cart_badge");

    // Collects all visible products from the home page and converts them into Product objects.
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
    
    /**
     * Clicks the shopping cart icon/link to navigate to the cart/checkout page.
     */
    public void navigateCheckoutPage(){
       driver.findElement(shoppingCartLink).click();
    }

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

    // Selects the requested sorting option from the dropdown on the home page.
    public void selectSortOption(SortType sortType) {

        Select select = new Select(driver.findElement(sortContainer));
        select.selectByVisibleText(sortType.getVisibleText());

    }

    // Adds one randomly selected product to the cart and returns its details.
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

    // Adds the requested number of products to the cart after shuffling the available items.
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

    // Returns the number shown on the shopping cart badge, or 0 if the cart is empty.
    public int getCartBadgeCount() {

        if (driver.findElements(shoppingCartBadge).isEmpty()) {
            return 0;
        }

        String badgeCount = driver.findElement(shoppingCartBadge).getText();
        return Integer.parseInt(badgeCount);

    }

}
