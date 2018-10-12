package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotNull;

public class FuncIMadeCommand {
    @NotNull
    private Long dishId;

    @Override
    public String toString() {
        return "FuncIMadeCommand{" +
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
