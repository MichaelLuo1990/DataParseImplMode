package com.example.jsonparse.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Desc Google-gson解析工具类
 * Created by Michael on 2018/4/19.
 */

public class GsonUtils {

    private static Gson gson = null;

    static {
        if (gson == null) {
            //gson = new Gson();
            //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }

    private GsonUtils() {
    }

    /**
     * object 转 jsonStr
     *
     * @param object
     * @return String
     */
    public static String obj2JsonStr(Object object) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(object);
        }
        return jsonStr;
    }

    /**
     * jsonStr 转 JavaBean
     *
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> T jsonStr2Bean(String jsonStr, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonStr, cls);//传入json对象和对象类型,将json转成对象
        }
        return t;
    }

    /**
     * jsonStr 转 list
     *
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> jsonStr2List(String jsonStr, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            //根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
            list = gson.fromJson(jsonStr, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * jsonStr 转 List<Map<String, T>>
     *
     * @param jsonStr
     * @return
     */
    public static <T> List<Map<String, T>> jsonStr2ListMap(String jsonStr) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(jsonStr,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * jsonStr 转 map
     *
     * @param jsonStr
     * @return
     */
    public static <T> Map<String, T> jsonStr2Map(String jsonStr) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(jsonStr, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
