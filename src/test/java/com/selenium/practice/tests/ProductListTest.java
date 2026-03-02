package com.selenium.practice.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.base.BaseTest;
import com.selenium.practice.models.Product;
import com.selenium.practice.pages.HomePage;

public class ProductListTest extends AuthenticatedBaseTest {

    @Test
    public void productNamePrice() {

        HomePage  homePage = new HomePage(driver);

        List<Product> products = homePage.getAllProducts();

        for (Product product : products){
            System.out.println(product);

        }
    	
    }
}
