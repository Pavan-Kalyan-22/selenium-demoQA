package com.selenium.practice.tests.ui.product;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.AuthenticatedBaseTest;
import com.selenium.practice.enums.SortCategory;
import com.selenium.practice.enums.SortType;
import com.selenium.practice.models.Product;
import com.selenium.practice.pages.HomePage;
import com.selenium.practice.utils.ProductSortUtils;

public class ProductListTest extends AuthenticatedBaseTest {

    @Test
    public void verifyAllSortingOptionsInSingleFlow() {

        HomePage homePage = new HomePage(driver);

        //  1. Verify Default Sorting (A → Z)
        List<Product> defaultProducts = homePage.getAllProducts();

        Assert.assertTrue(
                ProductSortUtils.isSortedByName(defaultProducts, true),
                "Default sorting is not Name A → Z"
        );

        //  2. Verify remaining 3 sorts
        verifySorting(homePage, SortType.NAME_DESC);
        verifySorting(homePage, SortType.PRICE_ASC);
        verifySorting(homePage, SortType.PRICE_DESC);
    }

    private void verifySorting(HomePage homePage, SortType sortType) {

        homePage.selectSortOption(sortType);

        List<Product> products = homePage.getAllProducts();

        boolean isSorted;

        if (sortType.getCategory() == SortCategory.NAME) {
            isSorted = ProductSortUtils.isSortedByName(products, sortType.isAscending());
        } else {
            isSorted = ProductSortUtils.isSortedByPrice(products, sortType.isAscending());
        }

        Assert.assertTrue(
                isSorted,
                "Sorting validation failed for: " + sortType
        );
    }
}