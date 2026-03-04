package com.selenium.practice.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.selenium.practice.models.Product;

public class ProductSortUtils {

    public static boolean isSortedByName(List<Product> products, boolean ascending) {

        List<Product> expectedList = new ArrayList<>(products);

        Comparator<Product> comparator =
                Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        expectedList.sort(comparator);

        return products.equals(expectedList);
    }

    public static boolean isSortedByPrice(List<Product> products, boolean ascending) {

        List<Product> expectedList = new ArrayList<>(products);

        Comparator<Product> comparator =
                Comparator.comparingDouble(Product::getPrice);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        expectedList.sort(comparator);

        return products.equals(expectedList);
    }
}