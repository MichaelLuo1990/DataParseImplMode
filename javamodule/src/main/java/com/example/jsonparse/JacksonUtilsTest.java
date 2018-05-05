package com.example.jsonparse;

import com.example.jsonparse.utils.JacksonUtils;
import com.example.jsonparse.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析测试
 */
public class JacksonUtilsTest {

    private static String jsonToList = "[{\n" +
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
        System.out.println("=============================读写json文件相关操作=======================");
        try {
            // 读取JSON数据
            Map<String,Object> userData = JacksonUtils.readRawJsonFile("javamodule/raw/user.json");
            System.out.println(userData);
            // 写入JSON数据
            userData = new HashMap<String,Object>();
            Map<String,String> nameStruct = new HashMap<String,String>();
            nameStruct.put("first", "Joe");
            nameStruct.put("last", "Hankcs");
            userData.put("name", nameStruct);
            userData.put("gender", "MALE");
            userData.put("verified", Boolean.FALSE);
            userData.put("userImage", "Rm9vYmFyIQ==");
            JacksonUtils.writeRawJsonFile("javamodule/raw/user-modified.json", userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=============================对象(javaBean、map、list)转json字符串=======================");
        try {
            User user = new User();
            user.setName("zhangsan");
            user.setEmail("zhangsan@163.com");
            user.setAge(20);
            String obj2Json = JacksonUtils.obj2Json(user);
            System.out.println(obj2Json);
            System.out.println("==========忽略null值========");
            String obj2jsonIgnoreNull = JacksonUtils.obj2jsonIgnoreNull(user);
            System.out.println(obj2jsonIgnoreNull);
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("name", "Michael");
            jsonMap.put("sex", "male");
            jsonMap.put("age", "28");
            String obj2JsonMap = JacksonUtils.obj2Json(jsonMap);
            System.out.println(obj2JsonMap);
            List<Map<String, Object>> lists = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("name", "lilei");
            map.put("age", "12");
            lists.add(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("name", "haimeimei");
            map1.put("age", "12");
            lists.add(map1);
            String obj2JsonList = JacksonUtils.obj2Json(lists);
            System.out.println(obj2JsonList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=============================对象(json字符串、map)转javaBean对象=======================");
        String map2Json = "";
        try {
            Map<String, Object> rawJsonFile = JacksonUtils.readRawJsonFile("javamodule/raw/jsonToJavaBean.json");
            map2Json = JacksonUtils.map2Json(rawJsonFile);
            User json2Bean = JacksonUtils.json2Bean(map2Json, User.class);
            System.out.println(json2Bean);
            User map2Bean = JacksonUtils.map2Bean(rawJsonFile, User.class);
            System.out.println(map2Bean);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=============================json转map、list=======================");
        try {
            Map<String, Object> json2map = JacksonUtils.json2map(map2Json);
            System.out.println(json2map);
            Map<String, Object> listMap = JacksonUtils.readRawJsonFile("javamodule/raw/jsonToList.json");
            List<User> users = (List<User>) listMap.get("listObj");
            System.out.println("listObjs:" + users);
            //  有异常抛出！！！
            List<User> user1 = JacksonUtils.json2list(jsonToList, new TypeReference<List<User>>() { });
            System.out.println(user1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
