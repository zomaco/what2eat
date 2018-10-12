package com.zomaco.what2eat.dao;

import com.zomaco.what2eat.pojo.Dish;
import com.zomaco.what2eat.util.FileUtil;
import com.zomaco.what2eat.util.JsonUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class DishOp extends BaseDao<Dish> {

    public DishOp(String dataSource) {
        super(dataSource, Dish.class);
    }

    @Override
    public void update(Dish dish) {
        try {
            List<Dish> data = JsonUtil.fromJsonFile(dataSource, Dish.class);
            for (Dish ent : data) {
                if (ent.getId() == dish.getId()) {
                    ent.setName(dish.getName());
                    ent.setCategory(dish.getCategory());
                    ent.setLastDate(dish.getLastDate());
                    ent.setHowToCook(dish.getHowToCook());
                    break;
                }
            }
            FileUtil.writeToFile(dataSource, JsonUtil.toJson(data));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
