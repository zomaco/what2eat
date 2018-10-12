package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotNull;

public class FuncDishDetailCommand {
    @NotNull
    private Long dishId;

    @Override
    public String toString() {
        return "FuncDishDetailCommand{" +
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
