package com.zomaco.what2eat.pojo;

import com.zomaco.what2eat.constant.Category;

import java.util.List;
import java.util.stream.Collectors;

public class FuncListMyDishesCommand {
    private List<Category> categories;
    private String keyword;

    @Override
    public String toString() {
        return "FuncListMyDishesCommand{" +
                "categories=" + categories +
                ", keyword='" + keyword + '\'' +
                '}';
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        if (categories != null) {
            this.categories = categories.stream().map(Category::getByValue).collect(Collectors.toList());
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
