package com.zomaco.what2eat.pojo;

public class FuncListMyIngredientsDto {
    private Long id;
    private String name;
    private int quantity;

    @Override
    public String toString() {
        return "FuncListMyIngredientsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
