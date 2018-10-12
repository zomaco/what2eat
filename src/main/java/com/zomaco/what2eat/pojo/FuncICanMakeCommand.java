package com.zomaco.what2eat.pojo;

import com.zomaco.what2eat.constant.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FuncICanMakeCommand {
    @NotBlank
    private String dishName;
    @NotNull
    private Category dishCategory;
    private List<Long> ingredientIds = new ArrayList<>();
    private List<String> ingredientNames = new ArrayList<>();
    private String howToCook;

    @Override
    public String toString() {
        return "FuncICanMakeCommand{" +
                "dishName='" + dishName + '\'' +
                ", dishCategory=" + dishCategory +
                ", ingredientIds=" + ingredientIds +
                ", ingredientNames=" + ingredientNames +
                ", howToCook='" + howToCook + '\'' +
                '}';
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Category getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(String dishCategory) {
        this.dishCategory = Category.getByValue(dishCategory);
    }

    public List<Long> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Long> ingredientIds) {
        if (ingredientIds != null) {
            this.ingredientIds = ingredientIds;
        }
    }

    public List<String> getIngredientNames() {
        return ingredientNames;
    }

    public void setIngredientNames(List<String> ingredientNames) {
        if (ingredientNames != null) {
            this.ingredientNames = ingredientNames;
        }
    }

    public String getHowToCook() {
        return howToCook;
    }

    public void setHowToCook(String howToCook) {
        this.howToCook = howToCook;
    }
}
