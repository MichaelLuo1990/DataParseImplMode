package com.example.jsonparse;

import com.example.jsonparse.entity.MoshiJavaBean;
import com.example.jsonparse.entity.Student;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc moshi json解析框架引用测试
 * API ref   https://github.com/square/moshi
 * Created by Michael on 2018/4/20.
 */

public class MoshiJsonTest {

    public static String jsonStr = "{\"code\":\"200\",\"message\":\"success\",\"resultData\":{\"area\":\"LA\",\"sex\":\"male\",\"birthday\":\"1990-08-07-\"}}";

    public static String jsonListStr = "[{\n" +
            "  \"name\": \"zhangsan\",\n" +
            "  \"age\": 20,\n" +
            "  \"sex\": \"male\"\n" +
            "},\n" +
            "  {\n" +
            "    \"name\": \"lisi\",\n" +
            "    \"age\": 21,\n" +
            "    \"sex\": \"female\"\n" +
            "  }, {\n" +
            "  \"name\": \"wangwu\",\n" +
            "  \"age\": 23,\n" +
            "  \"sex\": \"male\"\n" +
            "}\n" +
            "]";

    public static String jsonMapStr = "{\"name\":\"zhangsan\",\"age\":20,\"birthday\":\"1990-12-21\",\"email\":\"zhangsan@163.com\"}";

    public static void main(String args[]) {
        Moshi moshi = new Moshi.Builder().build();//构建moshi builder实例化对象
        JsonAdapter<MoshiJavaBean> jsonAdapter = moshi.adapter(MoshiJavaBean.class);//使用adapter绑定实体类
        System.out.println("============================jsonStr 转 JavaBean object============================");
        try {
            MoshiJavaBean moshiJavaBean = jsonAdapter.fromJson(jsonStr);
            System.out.println(moshiJavaBean);
            System.out.println("resultcode:" + moshiJavaBean.getCode()+ "    " +"reason:"+ moshiJavaBean.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("============================jsonStr 转 list============================");
        try {
            //只能处理基本数据类型实体类对象属性
            Type type = Types.newParameterizedType(List.class, Student.class);
            JsonAdapter<List<Student>> adapter = moshi.adapter(type);
            List<Student> students = adapter.fromJson(jsonListStr);
            System.out.println(students);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("============================jsonStr 转 map============================");
        try {
            final JsonAdapter<Map> jAdapter = moshi.adapter(Map.class);//使用adapter绑定实体类
            Map<String, Object> map = jAdapter.fromJson(jsonMapStr);
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("============================JavaBean object 转 jsonStr============================");
        MoshiJavaBean moshiJavaBean = new MoshiJavaBean();
        moshiJavaBean.setCode("200");
        moshiJavaBean.setMessage("success");
        MoshiJavaBean.ResultData resultData = new MoshiJavaBean.ResultData();
        resultData.setArea("China");
        resultData.setSex("female");
        resultData.setArea("1991-06-12");
        moshiJavaBean.setResultData(resultData);
        String toJson = jsonAdapter.toJson(moshiJavaBean);
        System.out.println(toJson);

        System.out.println("============================list 转 jsonStr============================");
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setSex("female");
        student.setAge(23);
        student.setName("Michael");
        Student student1 = new Student();
        student1.setSex("male");
        student1.setAge(21);
        student1.setName("Amy");
        students.add(student);
        students.add(student1);
        JsonAdapter<List> listJsonAdapter = moshi.adapter(List.class);
        String json = listJsonAdapter.toJson(students);
        System.out.println(json);

        System.out.println("============================map 转 jsonStr============================");
        Map<String, Object> map = new HashMap<>();
        map.put("student1", student);
        map.put("student2", student1);
        JsonAdapter<Map> mapJsonAdapter = moshi.adapter(Map.class);
        String toJsonMap = mapJsonAdapter.toJson(map);
        System.out.println(toJsonMap);

    }

}
