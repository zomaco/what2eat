package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FuncIBoughtCommand {
    @NotBlank
    private String ingredientName;
    @NotNull
    private Integer quantity;

    @Override
    public String toString() {
        return "FuncIBoughtCommand{" +
                "ingredientName='" + ingredientName + '\'' +
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
