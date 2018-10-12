package com.zomaco.what2eat.config;

import com.zomaco.what2eat.dao.DishOp;
import com.zomaco.what2eat.dao.IngredientOp;
import com.zomaco.what2eat.dao.RecipeOp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Value("${data.file.dish}")
    private String dishDataSource;

    @Value("${data.file.ingredient}")
    private String ingredientDataSource;

    @Value("${data.file.recipe}")
    private String recipeDataSource;

    @Bean
    public DishOp dishOp() {
        return new DishOp(dishDataSource);
    }

    @Bean
    public IngredientOp ingredientOp() {
        return new IngredientOp(ingredientDataSource);
    }

    @Bean
    public RecipeOp recipeOp() {
        return new RecipeOp(recipeDataSource);
    }
}
