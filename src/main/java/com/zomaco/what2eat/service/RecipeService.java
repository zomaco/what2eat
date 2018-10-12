package com.zomaco.what2eat.service;

import com.zomaco.what2eat.constant.Messages;
import com.zomaco.what2eat.dao.DishOp;
import com.zomaco.what2eat.dao.IngredientOp;
import com.zomaco.what2eat.dao.RecipeOp;
import com.zomaco.what2eat.pojo.FuncBindRecipeCommand;
import com.zomaco.what2eat.pojo.FuncUnbindRecipeCommand;
import com.zomaco.what2eat.pojo.Ingredient;
import com.zomaco.what2eat.pojo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private DishOp dishOp;
    @Autowired
    private IngredientOp ingredientOp;
    @Autowired
    private RecipeOp recipeOp;

    public void bindRecipe(FuncBindRecipeCommand command) {
        Long ingredientId;
        Ingredient ingredient = ingredientOp.selectByName(command.getIngredientName());
        if (ingredient != null) {
            ingredientId = ingredient.getId();
        } else {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(command.getIngredientName());
            ingredientOp.insert(newIngredient);
            ingredientId = newIngredient.getId();
        }
        Recipe newRecipe = new Recipe();
        newRecipe.setDishId(command.getDishId());
        newRecipe.setIngredientId(ingredientId);
        recipeOp.insert(newRecipe);
    }

    public String unbindRecipe(FuncUnbindRecipeCommand command) {
        Recipe recipe = recipeOp.selectByDishIdAndIngredientId(command.getDishId(), command.getIngredientId());
        if (recipe == null) {
            return Messages.M4;
        }
        recipeOp.delete(recipe.getId());
        return "";
    }
}
