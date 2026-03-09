package com.selenium.practice.enums;

public enum SortType {

    NAME_ASC("Name (A to Z)", true, SortCategory.NAME),
    NAME_DESC("Name (Z to A)", false, SortCategory.NAME),
    PRICE_ASC("Price (low to high)", true, SortCategory.PRICE),
    PRICE_DESC("Price (high to low)", false, SortCategory.PRICE);

    private final String visibleText;
    private final boolean ascending;
    private final SortCategory category;

    SortType(String visibleText, boolean ascending, SortCategory category) {
        this.visibleText = visibleText;
        this.ascending = ascending;
        this.category = category;
    }

    public String getVisibleText() {
        return visibleText;
    }

    public boolean isAscending() {
        return ascending;
    }

    public SortCategory getCategory() {
        return category;
    }
}