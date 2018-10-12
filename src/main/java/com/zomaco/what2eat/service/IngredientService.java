package com.zomaco.what2eat.service;

import com.zomaco.what2eat.constant.Messages;
import com.zomaco.what2eat.dao.DishOp;
import com.zomaco.what2eat.dao.IngredientOp;
import com.zomaco.what2eat.dao.RecipeOp;
import com.zomaco.what2eat.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    private DishOp dishOp;
    @Autowired
    private IngredientOp ingredientOp;
    @Autowired
    private RecipeOp recipeOp;

    public void iBought(FuncIBoughtCommand command) {
        Ingredient ingredient = ingredientOp.selectByName(command.getIngredientName());
        if (ingredient != null) {
            ingredient.setQuantity(ingredient.getQuantity() + command.getQuantity());
            ingredientOp.update(ingredient);
        } else {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(command.getIngredientName());
            newIngredient.setQuantity(command.getQuantity());
            ingredientOp.insert(newIngredient);
        }
    }

    public String iUsed(FuncIUsedCommand command) {
        Ingredient ingredient = ingredientOp.selectByName(command.getIngredientName());
        if (ingredient == null) {
            return Messages.M2;
        }
        if (command.getQuantity() != null && command.getQuantity() < ingredient.getQuantity()) {
            ingredient.setQuantity(ingredient.getQuantity() - command.getQuantity());
        } else {
            ingredient.setQuantity(0);
        }
        ingredientOp.update(ingredient);
        return "";
    }

    public List<FuncListMyIngredientsDto> listMyIngredients(FuncListMyIngredientsCommand command) {
        List<Ingredient> ingredients = ingredientOp.selectAll();
        if (command.getStockOnly()) {
            ingredients = ingredients.stream().filter(e -> e.getQuantity() > 0).collect(Collectors.toList());
        }
        return convertToDto(ingredients);
    }

    public String ingredientDelete(FuncIngredientDeleteCommand command) {
        List<Recipe> recipes = recipeOp.selectAll();
        if (recipes.stream().anyMatch(e -> e.getIngredientId() == command.getIngredientId())) {
            return Messages.M5;
        }
        Ingredient ingredient = ingredientOp.select(command.getIngredientId());
        if (ingredient != null) {
            ingredientOp.delete(ingredient.getId());
        }
        return "";
    }

    private List<FuncListMyIngredientsDto> convertToDto(List<Ingredient> ingredients) {
        return ingredients.stream().map(e -> {
            FuncListMyIngredientsDto dto = new FuncListMyIngredientsDto();
            dto.setId(e.getId());
            dto.setName(e.getName());
            dto.setQuantity(e.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }
}
