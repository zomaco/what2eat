package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotNull;

public class FuncUnbindRecipeCommand {
    @NotNull
    private Long dishId;
    @NotNull
    private Long ingredientId;

    @Override
    public String toString() {
        return "FuncUnbindRecipeCommand{" +
                "dishId=" + dishId +
                ", ingredientId=" + ingredientId +
                '}';
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
