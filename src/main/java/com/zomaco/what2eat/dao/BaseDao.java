package com.zomaco.what2eat.dao;

import com.zomaco.what2eat.pojo.BaseEntity;
import com.zomaco.what2eat.util.FileUtil;
import com.zomaco.what2eat.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDao<T extends BaseEntity> {
    protected static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
    protected String dataSource;
    private Class<T> tClass;

    public BaseDao(String dataSource, Class<T> tClass) {
        Assert.notNull(dataSource, "dataSource can not be null");
        Assert.notNull(tClass, "class can not be null");
        this.dataSource = dataSource;
        this.tClass = tClass;
    }

    public List<T> selectAll() {
        try {
            return JsonUtil.fromJsonFile(dataSource, tClass);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public T select(long id) {
        try {
            List<T> data = JsonUtil.fromJsonFile(dataSource, tClass);
            return data.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public void insert(T entity) {
        try {
            List<T> data = JsonUtil.fromJsonFile(dataSource, tClass);
            long newId = data.stream().map(T::getId).max(Long::compareTo).orElse(0L) + 1;
            entity.setId(newId);
            data.add(entity);
            FileUtil.writeToFile(dataSource, JsonUtil.toJson(data));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void delete(long id) {
        try {
            List<T> data = JsonUtil.fromJsonFile(dataSource, tClass);
            data = data.stream().filter(e -> e.getId() != id).collect(Collectors.toList());
            FileUtil.writeToFile(dataSource, JsonUtil.toJson(data));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public abstract void update(T entity);
}
