package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotNull;

public class FuncDishDeleteCommand {
    @NotNull
    private Long dishId;

    @Override
    public String toString() {
        return "FuncDishDeleteCommand{" +
                "dishId=" + dishId +
                '}';
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }
}
