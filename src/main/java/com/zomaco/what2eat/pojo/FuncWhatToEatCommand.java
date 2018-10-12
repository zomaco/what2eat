package com.zomaco.what2eat.pojo;

import com.zomaco.what2eat.constant.Category;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class FuncWhatToEatCommand {
    /**
     * 调用次数 前端从0开始累加
     */
    @NotNull
    private Integer callTimes;
    private List<Category> categories;

    @Override
    public String toString() {
        return "FuncWhatToEatCommand{" +
                "callTimes=" + callTimes +
                ", categories=" + categories +
                '}';
    }

    public Integer getCallTimes() {
        return callTimes;
    }

    public void setCallTimes(Integer callTimes) {
        this.callTimes = callTimes;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        if (categories != null) {
            this.categories = categories.stream().map(Category::getByValue).collect(Collectors.toList());
        }
    }
}
