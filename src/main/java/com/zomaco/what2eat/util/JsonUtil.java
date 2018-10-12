package com.zomaco.what2eat.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper MAPPER_EXCLUDE_NULL = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        MAPPER_EXCLUDE_NULL.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER_EXCLUDE_NULL.setSerializationInclusion(Include.NON_NULL);
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        return toJson(obj, MAPPER);
    }

    public static String toJsonIgnoreNull(Object obj) throws JsonProcessingException {
        return toJson(obj, MAPPER_EXCLUDE_NULL);
    }

    private static String toJson(Object obj, ObjectMapper objectMapper) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || "".equals(json)) {
            return null;
        }
        T t = null;
        try {
            t = MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }

    public static <T> List<T> toList(String jsonArray, Class<T> clazz) throws IOException {
        return MAPPER.readValue(jsonArray, MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <T> List<T> fromJsonFile(String fileName, Class<T> clazz) throws IOException {
        return MAPPER.readValue(new FileReader(fileName), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
