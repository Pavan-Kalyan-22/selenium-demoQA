package com.selenium.practice.enums;

// Represents the sorting options available in the application.
public enum SortType {

    // Sort names from A to Z.
    NAME_ASC("Name (A to Z)", true, SortCategory.NAME),
    // Sort names from Z to A.
    NAME_DESC("Name (Z to A)", false, SortCategory.NAME),
    // Sort prices from low to high.
    PRICE_ASC("Price (low to high)", true, SortCategory.PRICE),
    // Sort prices from high to low.
    PRICE_DESC("Price (high to low)", false, SortCategory.PRICE);

    // Text shown to the user for this sorting option.
    private final String visibleText;
    // Indicates whether the sorting order is ascending.
    private final boolean ascending;
    // The category this sort option belongs to.
    private final SortCategory category;

    // Initializes a sorting option with its display text, direction, and category.
    SortType(String visibleText, boolean ascending, SortCategory category) {
        this.visibleText = visibleText;
        this.ascending = ascending;
        this.category = category;
    }

    // Returns the text displayed for this sorting option.
    public String getVisibleText() {
        return visibleText;
    }

    // Returns whether this sorting option uses ascending order.
    public boolean isAscending() {
        return ascending;
    }

    // Returns the category associated with this sorting option.
    public SortCategory getCategory() {
        return category;
    }
}