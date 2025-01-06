package com.kumar.gamesstore.domain;

public enum HomeCategorySection {
    GRID("GRID"),
    SHOP_BY_CATEGORIES("SHOP_BY_CATEGORIES"),
    PC_GAMES_CATEGORIES("PC_GAMES_CATEGORIES"),
    DEALS("DEALS");

    private String value;

    HomeCategorySection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HomeCategorySection fromString(String section) {
        for (HomeCategorySection s : values()) {
            if (s.value.equalsIgnoreCase(section)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown section: " + section);
    }
}