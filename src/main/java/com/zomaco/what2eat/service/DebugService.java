package com.zomaco.what2eat.service;

import com.zomaco.what2eat.dao.DishOp;
import com.zomaco.what2eat.dao.IngredientOp;
import com.zomaco.what2eat.dao.RecipeOp;
import com.zomaco.what2eat.pojo.Dish;
import com.zomaco.what2eat.pojo.Ingredient;
import com.zomaco.what2eat.pojo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebugService {

    @Autowired
    private DishOp dishOp;
    @Autowired
    private IngredientOp ingredientOp;
    @Autowired
    private RecipeOp recipeOp;

    public static void main(String[] args) {
    }

    public List<Dish> dishList() {
        return dishOp.selectAll();
    }

    public List<Ingredient> ingredientList() {
        return ingredientOp.selectAll();
    }

    public List<Recipe> recipeList() {
        return recipeOp.selectAll();
    }
}
