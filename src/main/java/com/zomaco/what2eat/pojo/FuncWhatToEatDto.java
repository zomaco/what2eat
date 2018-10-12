package com.zomaco.what2eat.pojo;

public class FuncWhatToEatDto {
    private Long dishId;
    private String dishName;

    @Override
    public String toString() {
        return "FuncWhatToEatDto{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
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
}
