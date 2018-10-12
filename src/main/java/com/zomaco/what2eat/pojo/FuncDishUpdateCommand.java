package com.zomaco.what2eat.pojo;

import javax.validation.constraints.NotNull;

public class FuncDishUpdateCommand {
    @NotNull
    private Long dishId;
    private String howToCook;

    @Override
    public String toString() {
        return "FuncDishUpdateCommand{" +
                "dishId=" + dishId +
                ", howToCook='" + howToCook + '\'' +
                '}';
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getHowToCook() {
        return howToCook;
    }

    public void setHowToCook(String howToCook) {
        this.howToCook = howToCook;
    }
}
