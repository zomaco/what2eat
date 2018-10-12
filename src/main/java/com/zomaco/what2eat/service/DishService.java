package com.zomaco.what2eat.service;

import com.zomaco.what2eat.constant.Messages;
import com.zomaco.what2eat.dao.DishOp;
import com.zomaco.what2eat.dao.IngredientOp;
import com.zomaco.what2eat.dao.RecipeOp;
import com.zomaco.what2eat.pojo.*;
import com.zomaco.what2eat.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DishService {

    @Value("${w2e.ignore.days:2}")
    private int ignoreDays;

    @Autowired
    private DishOp dishOp;
    @Autowired
    private IngredientOp ingredientOp;
    @Autowired
    private RecipeOp recipeOp;

    public String iCanMake(FuncICanMakeCommand command) {
        List<Dish> dishes = dishOp.selectAll();
        List<Ingredient> ingredients = ingredientOp.selectAll();

        // 1.检查dishName的唯一性
        if (dishes.stream().anyMatch(e -> e.getName().equals(command.getDishName()))) {
            return Messages.M1;
        }
        // 2.把ingredientNames转换成id，原来不存在的name要新建
        List<Long> idFromNames = new ArrayList<>();
        for (String name : command.getIngredientNames()) {
            boolean found = false;
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getName().equals(name)) {
                    idFromNames.add(ingredient.getId());
                    found = true;
                    break;
                }
            }
            if (!found) {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(name);
                ingredientOp.insert(newIngredient);
                idFromNames.add(newIngredient.getId());
            }
        }
        // 3.插入数据
        Dish newDish = new Dish();
        newDish.setName(command.getDishName());
        newDish.setCategory(command.getDishCategory());
        newDish.setHowToCook(command.getHowToCook());
        dishOp.insert(newDish);

        List<Long> idsToInsert = new ArrayList<>();
        idsToInsert.addAll(idFromNames);
        idsToInsert.addAll(command.getIngredientIds());
        List<Recipe> recipesToInsert = idsToInsert.stream().map(e -> {
            Recipe newRecipe = new Recipe();
            newRecipe.setDishId(newDish.getId());
            newRecipe.setIngredientId(e);
            return newRecipe;
        }).collect(Collectors.toList());
        recipeOp.batchInsert(recipesToInsert);
        return "";
    }

    public List<FuncListMyDishesDto> listMyDishes(FuncListMyDishesCommand command) {
        List<Dish> dishes = dishOp.selectAll();
        List<Ingredient> ingredients = ingredientOp.selectAll();
        List<Recipe> recipes = recipeOp.selectAll();

        Predicate<Dish> searchCondition = e -> true;
        if (command.getCategories() != null) {
            searchCondition = searchCondition.and(e -> command.getCategories().contains(e.getCategory()));
        }
        if (StringUtil.isNotEmpty(command.getKeyword())) {
            List<Long> ingredientIds = ingredients.stream()
                    .filter(e -> e.getName().contains(command.getKeyword()))
                    .map(Ingredient::getId)
                    .collect(Collectors.toList());
            List<Long> dishIds = recipes.stream()
                    .filter(e -> ingredientIds.contains(e.getIngredientId()))
                    .map(Recipe::getDishId)
                    .collect(Collectors.toList());
            searchCondition = searchCondition.and(e -> e.getName().contains(command.getKeyword()) || dishIds.contains(e.getId()));
        }
        dishes = dishes.stream().filter(searchCondition).collect(Collectors.toList());
        return convertToDto(dishes);
    }

    public String iMade(FuncIMadeCommand command) {
        Dish dish = dishOp.select(command.getDishId());
        if (dish == null) {
            return Messages.M3;
        }
        dish.setLastDate(new Date());
        dishOp.update(dish);
        return "";
    }

    /**
     * 核心方法
     */
    public List<FuncWhatToEatDto> whatToEat(FuncWhatToEatCommand command) {
        List<Ingredient> ingredientsStock = ingredientOp.selectAll().stream().filter(e -> e.getQuantity() != 0).collect(Collectors.toList());
        List<Recipe> recipes = recipeOp.selectAll();

        // 排除N（可配置）天以前做过的菜
        // 排除前端画面上没选择的种类
        // 排除没有食材做不了的菜
        // 按时间正序排序
        List<Dish> dishesFiltered = dishOp.selectAll().stream()
                .filter(e -> e.getLastDate().compareTo(getDateRestriction()) < 0)
                .filter(e ->
                        command.getCategories() == null || command.getCategories().stream().anyMatch(category -> category == e.getCategory()))
                .filter(e ->
                        recipes.stream()
                                .filter(recipe -> recipe.getDishId() == e.getId())
                                .allMatch(recipe -> ingredientsStock.stream().anyMatch(ingredient -> ingredient.getId() == recipe.getIngredientId())))
                .collect(Collectors.toList());
        // 按调用次数依次跳过四道菜
        int filteredSize = dishesFiltered.size();
        int windowSize = 4;
        int skipSize = 0;
        if (filteredSize > windowSize) {
            skipSize = command.getCallTimes() * windowSize % filteredSize;
            // 防止出现只剩一道菜的情况
            if (skipSize == filteredSize - 1) {
                skipSize = filteredSize - windowSize;
            }
        }
        List<FuncWhatToEatDto> res = dishesFiltered.stream()
                .sorted(Comparator.comparing(Dish::getLastDate))
                .skip(skipSize)
                .limit(windowSize)
                .map(e -> {
                    FuncWhatToEatDto dto = new FuncWhatToEatDto();
                    dto.setDishId(e.getId());
                    dto.setDishName(e.getName());
                    return dto;
                }).collect(Collectors.toList());

        // 从四道菜中随机选两个
        List<FuncWhatToEatDto> ret = new ArrayList<>();
        if (res.size() > 1) {
            int randomA = new Random().nextInt(res.size());
            int randomB;
            do {
                randomB = new Random().nextInt(res.size());
            } while (randomB == randomA);
            ret.add(res.get(randomA));
            ret.add(res.get(randomB));
        } else {
            ret.addAll(res);
        }
        return ret;
    }

    public FuncDishDetailDto dishDetail(FuncDishDetailCommand command) {
        FuncDishDetailDto dto = new FuncDishDetailDto();
        Dish dish = dishOp.select(command.getDishId());
        if (dish == null) {
            return new FuncDishDetailDto();
        }
        dto.setDishId(dish.getId());
        dto.setDishName(dish.getName());
        dto.setHowToCook(dish.getHowToCook());

        List<Ingredient> ingredients = ingredientOp.selectAll();
        List<Recipe> recipes = recipeOp.selectAll();
        List<Long> dishNeeds = recipes.stream()
                .filter(e -> e.getDishId() == command.getDishId())
                .map(Recipe::getIngredientId)
                .collect(Collectors.toList());
        for (Long id : dishNeeds) {
            Ingredient ingredient = ingredients.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
            FuncListMyIngredientsDto subDto = new FuncListMyIngredientsDto();
            if (ingredient != null) {
                subDto.setId(id);
                subDto.setName(ingredient.getName());
                subDto.setQuantity(ingredient.getQuantity());
            } else {
                subDto.setId(id);
                subDto.setName("未知食材");
                subDto.setQuantity(0);
            }
            dto.getIngredients().add(subDto);
        }
        return dto;
    }

    public void dishDelete(FuncDishDeleteCommand command) {
        Dish dish = dishOp.select(command.getDishId());
        if (dish != null) {
            dishOp.delete(dish.getId());
        }
        recipeOp.deleteByDishId(command.getDishId());
    }

    public String dishUpdate(FuncDishUpdateCommand command) {
        Dish dish = dishOp.select(command.getDishId());
        if (dish == null) {
            return Messages.M3;
        }
        dish.setHowToCook(command.getHowToCook());
        dishOp.update(dish);
        return "";
    }

    private List<FuncListMyDishesDto> convertToDto(List<Dish> dishes) {
        return dishes.stream().map(e -> {
            FuncListMyDishesDto dto = new FuncListMyDishesDto();
            dto.setId(e.getId());
            dto.setName(e.getName());
            dto.setCategory(e.getCategory().toString());
            dto.setLastDate(new SimpleDateFormat("yyyy-MM-dd").format(e.getLastDate()));
            return dto;
        }).collect(Collectors.toList());
    }

    private Date getDateRestriction() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * ignoreDays);
        return cal.getTime();
    }
}
