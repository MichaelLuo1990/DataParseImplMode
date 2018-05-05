package com.example.jsonparse;

import com.example.jsonparse.utils.FastJsonUtils;
import com.example.jsonparse.utils.JacksonUtils;
import com.example.jsonparse.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc fastJson解析测试
 * Created by Michael on 2018/4/17.
 */

public class FastJsonUtilsTest {

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
        obj2JsonStrTest();
        beanListMap2JsonStrTest();
        jsonStr2BeanListMapTest();
        scfeatureTest();
    }

    /**
     * 对象 （javaBean、 array（数组）、list、map ） 转 json字符串
     */
    public static void obj2JsonStrTest(){
        System.out.println("========================为空字段会显示=====================");
        User user = new User();
        user.setName("zhangsan");
        user.setBirthday(new Date(System.currentTimeMillis()));
        user.setEmail("zhangsan@163.com");
        user.setAge(20);
        //javaBean -》 jsonStr
//        String toJavaBeanJSONString = JSON.toJSONString(user);
        String toJavaBeanJSONString = FastJsonUtils.obj2JsonStr(user);
        System.out.println("========================javaBean obj  -》 jsonStr=====================");
        System.out.println(toJavaBeanJSONString);
        User user1 = new User();
        user1.setName("lisi");
        user1.setBirthday(new Date(System.currentTimeMillis()));
        user1.setEmail("lisi@163.com");
        user1.setAge(21);
        //Bean array -》 jsonStr
        User [] userArray = new User[2];
        userArray[0] = user;
        userArray[1] = user1;
//        String toArrayJSONString = JSON.toJSONString(userArray);
        String toArrayJSONString = FastJsonUtils.obj2JsonStr(userArray);
        System.out.println("========================Bean array  -》 jsonStr=====================");
        System.out.println(toArrayJSONString);
        //List<Bean> -》 jsonStr
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
//        String toListJSONString = JSON.toJSONString(userList);
        String toListJSONString = FastJsonUtils.obj2JsonStr(userList);
        System.out.println("========================List<Bean>  -》 jsonStr=====================");
        System.out.println(toListJSONString);
        //Map<String, Object>  -》 jsonStr
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Michael");
        map.put("age", "19");
        map.put("sex", "male");
        Map<String, Object> m1 = new HashMap<>();
        m1.put("champion", "1996");
        m1.put("champion", "1997");
        m1.put("champion", "1998");
        map.put("career", m1);
        map.put("users", userList);
//        String toMapJSONString = JSON.toJSONString(map);
        String toMapJSONString = FastJsonUtils.obj2JsonStr(map);
        System.out.println("========================Map<String, Object>  -》 jsonStr=====================");
        System.out.println(toMapJSONString);
        //List<Map<String, Object>>  -》 jsonStr
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "kobe");
        map1.put("age", "18");
        map1.put("sex", "male");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "yao");
        map2.put("age", "22");
        map2.put("sex", "male");
        List<Map<String, Object>> lms = new ArrayList<>();
        lms.add(map);
        lms.add(map1);
        lms.add(map2);
//        String toLMJSONString = JSON.toJSONString(lms);
        String toLMJSONString = FastJsonUtils.obj2JsonStr(lms);
        System.out.println("========================List<Map<String, Object>>  -》 jsonStr=====================");
        System.out.println(toLMJSONString);
    }

    /**
     * javaBean obj、 list、 map  转  json字符串
     */
    public static void beanListMap2JsonStrTest() {
        System.out.println("========================为空字段不显示（过滤为null字段）=====================");
        //javabean对象  转  jsonStr
        User user = new User();
        user.setName("zhangsan");
        user.setBirthday(new Date(System.currentTimeMillis()));
        user.setEmail(null);//该字段在json中不显示
        user.setAge(20);
        String bean2JsonStr = FastJsonUtils.bean2JsonStr(user);
        System.out.println("========================javabean对象  -》 jsonStr=====================");
        System.out.println(bean2JsonStr);

        //list 转 jsonStr
        List<String> stringList = new ArrayList<>();
        stringList.add("asdfsdf");
        stringList.add(null);//该字段在json中不显示
        stringList.add("fdgifdghd");
        String list2JsonStr = FastJsonUtils.list2JsonStr(stringList);
        System.out.println("========================list  -》 jsonStr=====================");
        System.out.println(list2JsonStr);

        //map 转 jsonStr
        Map < String , Object > jsonMap = new HashMap<>();
        jsonMap.put("a",1);
        jsonMap.put("b","");
        jsonMap.put("c",null);//该字段在json中不显示
        jsonMap.put("d","wsadfgasdf");
        String map2JsonStr = FastJsonUtils.map2JsonStr(jsonMap);
        System.out.println("========================map  -》 jsonStr=====================");
        System.out.println(map2JsonStr);

    }

    /**
     * json字符串  转  javaBean obj、 list、 map
     */
    public static void jsonStr2BeanListMapTest() {
        System.out.println("========================json字符串  转  javaBean obj、 list、 map=====================");
        try {
            Map<String,Object> userData = JacksonUtils.readRawJsonFile("javamodule/raw/jsonToJavaBean.json");
            String obj2JsonStr = FastJsonUtils.obj2JsonStr(userData);
            //jsonStr 转 javaBean
            User jsonStr2Bean = FastJsonUtils.jsonStr2Bean(obj2JsonStr, User.class);
            System.out.println("========================jsonStr  -》 javaBean=====================");
            System.out.println(jsonStr2Bean);
            //jsonStr 转 list
            List<User> users = FastJsonUtils.jsonStr2List(jsonToList, User.class);
            System.out.println("========================jsonStr  -》 list=====================");
            System.out.println(users);
            //jsonStr 转 map
            Map jsonStr2Map = FastJsonUtils.jsonStr2Map(obj2JsonStr);
            System.out.println("========================jsonStr  -》 map=====================");
            System.out.println(jsonStr2Map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SerializeConfig   SerializerFeature 特性添加、显示测试
     */
    public static void scfeatureTest() {
        //SerializeConfig   SerializerFeature  测试日期格式、对象为空情况默认值显示
        Map<String, Object> scfMap = new HashMap<>();
        List list = null;
        Integer num = null;
        Boolean bool = null;
        String str = null;
        scfMap.put("list", list);
        scfMap.put("num", num);
        scfMap.put("bool", bool);
        scfMap.put("string", str);
        String scf2JsonStr = FastJsonUtils.obj2JsonStr(scfMap);
        System.out.println("========================SerializeConfig   SerializerFeature  测试日期格式、对象为空情况默认值显示  -》 jsonStr=====================");
        System.out.println("========================SerializeConfig   SerializerFeature  测试对象为空情况默认值显示  -》 jsonStr=====================");
        System.out.println(scf2JsonStr);

        System.out.println("========================SerializeConfig   SerializerFeature  测试兼容的日期输出格式显示  -》 jsonStr=====================");
        Map<String, Object> map = new HashMap<>();
        Date date = new Date(System.currentTimeMillis());
        map.put("date", date);
        String obj2JsonStr = FastJsonUtils.obj2JsonStr(map);
        System.out.println(obj2JsonStr);//输出date对象的json字符串
        System.out.println("========================无配置SerializeConfig   SerializerFeature  特性显示  -》 jsonStr=====================");
        String obj2NoFeatruesJsonStr = FastJsonUtils.obj2NoFeatruesJsonStr(map);
        System.out.println(obj2NoFeatruesJsonStr);//输出时间戳

        System.out.println("========================完美格式化输出json显示  -》 jsonStr=====================");
        Map<String,Object> jsonMap;
        try {
            jsonMap = JacksonUtils.readRawJsonFile("javamodule/raw/jsonToList.json");
            String obj2PrettyJsonStr = FastJsonUtils.obj2PrettyJsonStr(jsonMap);
            System.out.println(obj2PrettyJsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
