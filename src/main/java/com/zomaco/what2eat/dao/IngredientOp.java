package com.zomaco.what2eat.dao;

import com.zomaco.what2eat.pojo.Ingredient;
import com.zomaco.what2eat.util.FileUtil;
import com.zomaco.what2eat.util.JsonUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class IngredientOp extends BaseDao<Ingredient> {

    public IngredientOp(String dataSource) {
        super(dataSource, Ingredient.class);
    }

    @Override
    public void update(Ingredient ingredient) {
        try {
            List<Ingredient> data = JsonUtil.fromJsonFile(dataSource, Ingredient.class);
            for (Ingredient ent : data) {
                if (ent.getId() == ingredient.getId()) {
                    ent.setName(ingredient.getName());
                    ent.setQuantity(ingredient.getQuantity());
                    break;
                }
            }
            FileUtil.writeToFile(dataSource, JsonUtil.toJson(data));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Ingredient selectByName(String name) {
        try {
            List<Ingredient> data = JsonUtil.fromJsonFile(dataSource, Ingredient.class);
            return data.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
