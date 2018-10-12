package com.zomaco.what2eat.controller;

import com.zomaco.what2eat.constant.ResultCode;
import com.zomaco.what2eat.pojo.*;
import com.zomaco.what2eat.service.DishService;
import com.zomaco.what2eat.service.IngredientService;
import com.zomaco.what2eat.service.RecipeService;
import com.zomaco.what2eat.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/func")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private DishService dishService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/iCanMake", method = RequestMethod.POST)
    public CommonResult funcICanMake(@Validated @RequestBody FuncICanMakeCommand command) {
        CommonResult result = new CommonResult();
        try {
            String errMsg = dishService.iCanMake(command);
            if (StringUtil.isNotEmpty(errMsg)) {
                result.setCode(ResultCode.CLIENT_ERROR.code());
                result.setMessage(errMsg);
                return result;
            }
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/iBought", method = RequestMethod.POST)
    public CommonResult funcIBought(@Validated @RequestBody FuncIBoughtCommand command) {
        try {
            ingredientService.iBought(command);
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/iUsed", method = RequestMethod.POST)
    public CommonResult funcIUsed(@Validated @RequestBody FuncIUsedCommand command) {
        CommonResult result = new CommonResult();
        try {
            String errMsg = ingredientService.iUsed(command);
            if (StringUtil.isNotEmpty(errMsg)) {
                result.setCode(ResultCode.CLIENT_ERROR.code());
                result.setMessage(errMsg);
                return result;
            }
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/listMyDishes", method = RequestMethod.POST)
    public CommonResult funcListMyDishes(@Validated @RequestBody FuncListMyDishesCommand command) {
        try {
            CommonResult<List<FuncListMyDishesDto>> result = new CommonResult<>(ResultCode.OK.code());
            result.setData(dishService.listMyDishes(command));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/listMyIngredients", method = RequestMethod.POST)
    public CommonResult funcListMyIngredients(@Validated @RequestBody FuncListMyIngredientsCommand command) {
        try {
            CommonResult<List<FuncListMyIngredientsDto>> result = new CommonResult<>(ResultCode.OK.code());
            result.setData(ingredientService.listMyIngredients(command));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/iMade", method = RequestMethod.POST)
    public CommonResult funcIMade(@Validated @RequestBody FuncIMadeCommand command) {
        CommonResult result = new CommonResult();
        try {
            String errMsg = dishService.iMade(command);
            if (StringUtil.isNotEmpty(errMsg)) {
                result.setCode(ResultCode.CLIENT_ERROR.code());
                result.setMessage(errMsg);
                return result;
            }
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/whatToEat", method = RequestMethod.POST)
    public CommonResult funcWhatToEat(@Validated @RequestBody FuncWhatToEatCommand command) {
        try {
            CommonResult<List<FuncWhatToEatDto>> result = new CommonResult<>(ResultCode.OK.code());
            result.setData(dishService.whatToEat(command));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/bindRecipe", method = RequestMethod.POST)
    public CommonResult funcBindRecipe(@Validated @RequestBody FuncBindRecipeCommand command) {
        try {
            recipeService.bindRecipe(command);
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/unbindRecipe", method = RequestMethod.POST)
    public CommonResult funcUnbindRecipe(@Validated @RequestBody FuncUnbindRecipeCommand command) {
        CommonResult result = new CommonResult();
        try {
            String errMsg = recipeService.unbindRecipe(command);
            if (StringUtil.isNotEmpty(errMsg)) {
                result.setCode(ResultCode.CLIENT_ERROR.code());
                result.setMessage(errMsg);
                return result;
            }
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/dishDetail", method = RequestMethod.POST)
    public CommonResult funcDishDetail(@Validated @RequestBody FuncDishDetailCommand command) {
        try {
            CommonResult<FuncDishDetailDto> result = new CommonResult<>(ResultCode.OK.code());
            result.setData(dishService.dishDetail(command));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/dishDelete", method = RequestMethod.POST)
    public CommonResult funcDishDelete(@Validated @RequestBody FuncDishDeleteCommand command) {
        try {
            dishService.dishDelete(command);
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/dishUpdate", method = RequestMethod.POST)
    public CommonResult funcDishUpdate(@Validated @RequestBody FuncDishUpdateCommand command) {
        CommonResult result = new CommonResult();
        try {
            String errMsg = dishService.dishUpdate(command);
            if (StringUtil.isNotEmpty(errMsg)) {
                result.setCode(ResultCode.CLIENT_ERROR.code());
                result.setMessage(errMsg);
                return result;
            }
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }

    @RequestMapping(value = "/ingredientDelete", method = RequestMethod.POST)
    public CommonResult funcIngredientDelete(@Validated @RequestBody FuncIngredientDeleteCommand command) {
        CommonResult result = new CommonResult();
        try {
            String errMsg = ingredientService.ingredientDelete(command);
            if (StringUtil.isNotEmpty(errMsg)) {
                result.setCode(ResultCode.CLIENT_ERROR.code());
                result.setMessage(errMsg);
                return result;
            }
            return new CommonResult(ResultCode.OK.code());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommonResult(ResultCode.SERVER_ERROR.code());
        }
    }
}
