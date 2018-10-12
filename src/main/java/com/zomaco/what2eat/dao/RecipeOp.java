package com.zomaco.what2eat.dao;

import com.zomaco.what2eat.pojo.Recipe;
import com.zomaco.what2eat.util.FileUtil;
import com.zomaco.what2eat.util.JsonUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RecipeOp extends BaseDao<Recipe> {

    public RecipeOp(String dataSource) {
        super(dataSource, Recipe.class);
    }

    @Override
    public void update(Recipe recipe) {
        try {
            List<Recipe> data = JsonUtil.fromJsonFile(dataSource, Recipe.class);
            for (Recipe ent : data) {
                if (ent.getId() == recipe.getId()) {
                    ent.setDishId(recipe.getDishId());
                    ent.setIngredientId(recipe.getIngredientId());
                    break;
                }
            }
            FileUtil.writeToFile(dataSource, JsonUtil.toJson(data));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void batchInsert(List<Recipe> entities) {
        try {
            List<Recipe> data = JsonUtil.fromJsonFile(dataSource, Recipe.class);
            long maxId = data.stream().map(Recipe::getId).max(Long::compareTo).orElse(0L);
            int i = 1;
            for (Recipe ent : entities) {
                ent.setId(maxId + i);
                i++;
                data.add(ent);
            }
            FileUtil.writeToFile(dataSource, JsonUtil.toJson(data));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Recipe selectByDishIdAndIngredientId(Long dishId, Long ingredientId) {
        try {
            List<Recipe> data = JsonUtil.fromJsonFile(dataSource, Recipe.class);
            return data.stream().filter(e -> e.getDishId() == dishId && e.getIngredientId() == ingredientId).findFirst().orElse(null);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public void deleteByDishId(Long dishId) {
        try {
            List<Recipe> data = JsonUtil.fromJsonFile(dataSource, Recipe.class);
            data = data.stream().filter(e -> e.getDishId() != dishId).collect(Collectors.toList());
            FileUtil.writeToFile(dataSource, JsonUtil.toJson(data));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
