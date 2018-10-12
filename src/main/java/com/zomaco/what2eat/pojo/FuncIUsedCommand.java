package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotBlank;

public class FuncIUsedCommand {
    @NotBlank
    private String ingredientName;
    private Integer quantity;

    @Override
    public String toString() {
        return "FuncIUsedCommand{" +
                "ingredientName=" + ingredientName +
                ", quantity=" + quantity +
                '}';
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
