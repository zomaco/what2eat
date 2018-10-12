package com.zomaco.what2eat.controller;

import com.zomaco.what2eat.pojo.Dish;
import com.zomaco.what2eat.pojo.Ingredient;
import com.zomaco.what2eat.pojo.Recipe;
import com.zomaco.what2eat.service.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dbg")
public class DebugController {

    @Autowired
    private DebugService debugService;

    @RequestMapping(value = "/test")
    public List<Dish> test() {
        return debugService.dishList();
    }

    @RequestMapping(value = "/test1")
    public List<Ingredient> test1() {
        return debugService.ingredientList();
    }

    @RequestMapping(value = "/test12")
    public List<Recipe> test12() {
        return debugService.recipeList();
    }
}
