package com.zomaco.what2eat.pojo;

import java.util.ArrayList;
import java.util.List;

public class FuncDishDetailDto {
    private Long dishId;
    private String dishName;
    private List<FuncListMyIngredientsDto> ingredients = new ArrayList<>();
    private String howToCook;

    @Override
    public String toString() {
        return "FuncDishDetailDto{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", ingredients=" + ingredients +
                ", howToCook='" + howToCook + '\'' +
                '}';
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public List<FuncListMyIngredientsDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<FuncListMyIngredientsDto> ingredients) {
        this.ingredients = ingredients;
    }

    public String getHowToCook() {
        return howToCook;
    }

    public void setHowToCook(String howToCook) {
        this.howToCook = howToCook;
    }
}
