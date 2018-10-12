package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotNull;

public class FuncIngredientDeleteCommand {
    @NotNull
    private Long ingredientId;

    @Override
    public String toString() {
        return "FuncIngredientDeleteCommand{" +
                "ingredientId=" + ingredientId +
                '}';
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
