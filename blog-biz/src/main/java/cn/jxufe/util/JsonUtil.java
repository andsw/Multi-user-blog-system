package cn.jxufe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hsw
 * @date 2020/2/29 10:56 下午
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static <T> String object2Json(T o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.warn("error happens in generation from object to json", e);
        }
        return "error in json util";
    }

    public static <T> T json2Object(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            logger.warn("error happens in generation from json to object", e);
        }
        return null;
    }
}
