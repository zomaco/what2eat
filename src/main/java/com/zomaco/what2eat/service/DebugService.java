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
        for (int filteredSize = 0; filteredSize < 13; filteredSize++) {
            System.out.printf("\nsize\t%4d\n", filteredSize);
            for (int callTimes = 0; callTimes < 11; callTimes++) {
                int windowSize = 4;
                int skipSize = 0;
                if (filteredSize > windowSize) {
                    skipSize = callTimes * windowSize % filteredSize;
                    // 防止出现只剩一道菜的情况
                    if (skipSize == filteredSize - 1) {
                        skipSize = filteredSize - windowSize;
                    }
                }
                System.out.printf("call\t%4d skip\t%4d get\t%4d\n", callTimes, skipSize, filteredSize - skipSize);
            }
        }
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
