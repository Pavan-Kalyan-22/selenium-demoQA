package com.selenium.practice.models;

import java.util.Objects;

// Represents a product with a name and a price.
public class Product {

    // Stores the product name.
    private String name;

    // Stores the product price.
    private double price;

    // Creates a new product with the provided name and price.
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    // Returns the product name.
    public String getName(){
        return name;
    }

    // Returns the product price.
    public double getPrice(){
        return price;
    }

    // Returns a readable string representation of the product.
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }

    // Compares two products based on their name and price.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    // Generates a hash code based on the product's name and price.
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

}
