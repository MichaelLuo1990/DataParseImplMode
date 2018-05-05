package com.example.jsonparse.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Desc jackson解析json库工具类
 * Created by Michael on 2018/4/12.
 */

public class JacksonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtils() {

    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 读取当前工程目录资源json文件-测试使用；实际已http请求返回json为主
     *
     * @param fileName
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T readRawJsonFile(String fileName) throws IOException {
        return (T) objectMapper.readValue(new File(fileName), Map.class);
    }

    /**
     * 写入当前工程目录资源json文件-测试使用
     *
     * @param fileName
     * @param element
     * @param <T>
     * @throws IOException
     */
    public static <T> void writeRawJsonFile(String fileName, T element) throws IOException {
        objectMapper.writeValue(new File(fileName), element);
    }

    /**
     * 对象(javaBean、map、list、复杂类型组合)转json字符串
     *
     * @param obj 对象
     * @return json字符串
     * @throws Exception
     */
    public static String obj2Json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 对象(javaBean、map、list、复杂类型组合)转json字符串-忽略null值
     *
     * @param obj 对象
     * @return json字符串
     * @throws Exception
     */
    public static String obj2jsonIgnoreNull(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    /**
     * json 转JavaBean
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T json2Bean(String jsonString, Class<T> clazz) throws Exception {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * map 转JavaBean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T map2Bean(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * json字符串转换为map-如果map内部的value存在jsonString，继续解析
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2map(String json) throws Exception {
        return json2MapRecursion(json, objectMapper);
    }

    /**
     * json解析成map递归
     *
     * @param json
     * @param mapper
     * @return
     * @throws Exception
     */
    private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }
        Map<String, Object> map = mapper.readValue(json, Map.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);
                if (str.startsWith("[")) {
                    List<?> list = json2ListRecursion(str, mapper);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }
        return map;
    }

    /**
     * json解析成list递归
     *
     * @param json
     * @param mapper 解析工具
     * @return
     * @throws Exception
     */
    private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }
        List<Object> list = mapper.readValue(json, List.class);
        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = json2ListRecursion(str, mapper);
                } else if (obj.toString().startsWith("{")) {
                    obj = json2MapRecursion(str, mapper);
                }
            }
        }
        return list;
    }

    /**
     * json数组字符串转list
     */
    public static <T> List<T> json2list(String jsonArrayStr, TypeReference typeReference) throws Exception {
        return (List<T>) objectMapper.readValue(jsonArrayStr, typeReference);
    }

    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        List<T> lst = (List<T>) objectMapper.readValue(jsonArrayStr, javaType);
        return lst;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    /**
     * map 转json
     *
     * @param map
     * @return
     */
    public static String map2Json(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
