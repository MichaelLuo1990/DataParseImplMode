package com.example.jsonparse;

import com.example.jsonparse.utils.GsonUtils;
import com.example.jsonparse.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc gson解析测试
 * Created by Michael on 2018/4/19.
 */

public class GsonUtilsTest {

    public static String jsonBeanStr = "{\"name\":\"zhangsan\",\"age\":20,\"birthday\":\"1990-12-21\",\"email\":\"zhangsan@163.com\"}";
    public static String jsonListBean = "[{\n" +
            "  \"name\": \"zhangsan\",\n" +
            "  \"age\": 20,\n" +
            "  \"birthday\": \"1990-12-21\",\n" +
            "  \"email\": \"zhangsan@163.com\"\n" +
            "},\n" +
            "  {\n" +
            "    \"name\": \"lisi\",\n" +
            "    \"age\": 21,\n" +
            "    \"birthday\": \"1991-10-11\",\n" +
            "    \"email\": \"lisi@163.com\"\n" +
            "  }, {\n" +
            "  \"name\": \"wangwu\",\n" +
            "  \"age\": 23,\n" +
            "  \"birthday\": \"1995-04-21\",\n" +
            "  \"email\": \"wangwu@163.com\"\n" +
            "}\n" +
            "]";

    public static void main(String args[]) {
        //object 转 jsonStr
        User user = new User();
        user.setName("zhangsan");
        user.setBirthday(new Date(System.currentTimeMillis()));
        user.setEmail("zhangsan@163.com");
        user.setAge(20);
        String obj2JsonStr = GsonUtils.obj2JsonStr(user);
        System.out.println("=============================================object 转 jsonStr============================================");
        System.out.println(obj2JsonStr);
        System.out.println("=============================================List<Map<String, T>> 转 jsonStr============================================");
        List<Map<String, Object>> lms = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("aaa", "asdfsdf");
        map.put("bbb", true);
        map.put("ccc", 657);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("ddd", "hjggh");
        map1.put("eee", false);
        map1.put("fff", 4568);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("ggg", "dfgsdf");
        map2.put("hhh", false);
        map2.put("iii", 567);
        lms.add(map);
        lms.add(map1);
        lms.add(map2);
        String obj2JsonStr1 = GsonUtils.obj2JsonStr(lms);
        System.out.println(obj2JsonStr1);

        //jsonStr 转 JavaBean  list  List<Map<String, T>>  map
        System.out.println("=============================================jsonStr 转 JavaBean============================================");
        User jsonStr2Bean = GsonUtils.jsonStr2Bean(jsonBeanStr, User.class);
        System.out.println(jsonStr2Bean);
        System.out.println("=============================================jsonStr 转 list============================================");
        List<User> users = GsonUtils.jsonStr2List(jsonListBean, User.class);
        System.out.println(users);
        System.out.println("=============================================jsonStr 转 map============================================");
        Map<String, Object> jsonStr2Map = GsonUtils.jsonStr2Map(jsonBeanStr);
        System.out.println(jsonStr2Map);
        System.out.println("=============================================jsonStr 转 List<Map<String, T>>============================================");
        List<Map<String, Object>> jsonStr2ListMap = GsonUtils.jsonStr2ListMap(obj2JsonStr1);
        System.out.println(jsonStr2ListMap);

    }
}
