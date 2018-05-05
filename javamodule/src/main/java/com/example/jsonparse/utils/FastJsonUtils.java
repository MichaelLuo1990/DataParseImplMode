package com.example.jsonparse.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Desc fastjson工具类
 * Created by Michael on 2018/4/17.
 */

public class FastJsonUtils {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符串类型字段如果为null，输出为""，而不是null
    };

    /**
     * 完美格式化输出json字符串
     * @param object
     * @return
     */
    public static String obj2PrettyJsonStr(Object object) {
        return JSON.toJSONString(object, true);
    }

    /**
     * 对象（javaBean、 array（数组）、list、map ）转json字符串（包含为空处理显示）
     * @param object
     * @return 序列化特性json字符串
     */
    public static String obj2JsonStr(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 对象（javaBean、 array（数组）、list、map ）转json字符串（包含为空处理显示）
     * @param object
     * @return 无序列化特性json字符串
     */
    public static String obj2NoFeatruesJsonStr(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * javaBean 转 jsonStr
     * @param object
     * @return
     */
    public static String bean2JsonStr(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * list 转 jsonStr
     * @param list
     * @return
     */
    public static String list2JsonStr(List list) {
        List filterList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                filterList.add(list.get(i));
            }
        }
        return JSONArray.toJSONString(filterList);
    }

    /**
     * map 转 jsonStr
     * @param map
     * @return
     */
    public static String map2JsonStr(Map map) {
        return JSONObject.toJSONString(map);
    }

    /**
     * jsonStr 转 javaBean
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonStr2Bean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * jsonStr 转 list
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonStr2List(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * jsonStr 转 map
     * @param s
     * @return
     */
    public static Map jsonStr2Map(String s) {
        return JSONObject.parseObject(s);
    }

}
