package com.zomaco.what2eat.constant;

public enum Category {
    /**
     * 菜品或食材常见程度
     */
    LEVEL1(1, "十分常见"),
    LEVEL2(2, "一般常见"),
    LEVEL3(3, "较不常见"),
    LEVEL4(4, "极不常见"),
    LEVEL5(5, "早餐");

    int code;
    String value;

    Category(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Category getByValue(String value) {
        for (Category category : Category.values()) {
            if (category.value.equals(value)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
