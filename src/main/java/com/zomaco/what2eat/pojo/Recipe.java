package com.zomaco.what2eat.pojo;

public class Recipe extends BaseEntity {
    private long dishId;
    private long ingredientId;

    @Override
    public String toString() {
        return "Recipe{" +
                "dishId=" + dishId +
                ", ingredientId=" + ingredientId +
                "} " + super.toString();
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
