package com.zomaco.what2eat.pojo;

import com.zomaco.what2eat.constant.Category;

import java.util.Date;

public class Dish extends BaseEntity {
    private String name;
    private Category category;
    private Date lastDate = new Date(0);
    private String howToCook;

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", lastDate=" + lastDate +
                ", howToCook='" + howToCook + '\'' +
                "} " + super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getHowToCook() {
        return howToCook;
    }

    public void setHowToCook(String howToCook) {
        this.howToCook = howToCook;
    }
}
