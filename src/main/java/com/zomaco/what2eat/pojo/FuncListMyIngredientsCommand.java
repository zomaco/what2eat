package com.zomaco.what2eat.pojo;

public class FuncListMyIngredientsCommand {
    private boolean stockOnly;

    @Override
    public String toString() {
        return "FuncListMyIngredientsCommand{" +
                "stockOnly=" + stockOnly +
                '}';
    }

    public boolean getStockOnly() {
        return stockOnly;
    }

    public void setStockOnly(boolean stockOnly) {
        this.stockOnly = stockOnly;
    }
}
