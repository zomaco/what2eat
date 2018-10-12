package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FuncBindRecipeCommand {
    @NotNull
    private Long dishId;
    @NotBlank
    private String ingredientName;

    @Override
    public String toString() {
        return "FuncBindRecipeCommand{" +
                "dishId=" + dishId +
                ", ingredientName='" + ingredientName + '\'' +
                '}';
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
