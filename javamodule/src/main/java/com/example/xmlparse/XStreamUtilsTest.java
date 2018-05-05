package com.example.xmlparse;

import com.example.xmlparse.entity.Birthday;
import com.example.xmlparse.entity.Role;
import com.example.xmlparse.entity.Student;
import com.example.xmlparse.entity.User;
import com.example.xmlparse.utils.XStreamUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc xStream工具类测试
 * Created by Michael on 2018/4/23.
 */

public class XStreamUtilsTest {

    public static void main(String args[]) {
        System.out.println("=========================对象(JavaBean、list、map) 转 xml==========================");
        try {
            User user = new User();
            user.setId(1);
            user.setName("Test");
            user.setAge(20);
            user.setSex("1");
            user.setBirthday(new Date());
            Role role = new Role();
            role.setId(1);
            role.setName("角色1");
            Role role2 = new Role();
            role2.setId(2);
            role2.setName("角色2");
            List<Role> roles = new ArrayList<Role>();
            roles.add(role);
            roles.add(role2);
            user.setRoles(roles);
            String xml = XStreamUtils.beanToXml(user);
            System.out.println("=========================JavaBean 转 xml==========================");
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=========================list 转 xml==========================");
        try {
            List<Object> list = new ArrayList<Object>();
            Student student = new Student();
            student.setAddress("china");
            student.setEmail("tom@125.com");
            student.setId(2);
            student.setName("tom");
            Birthday birthday = new Birthday("2010-11-22");
            student.setBirthday(birthday);
            list.add(student);
            Map<String, Class<?>> classMap = new HashMap<>();
            classMap.put("student", Student.class);
            String listToXml = XStreamUtils.listMapToXml(list, classMap, "student");
            System.out.println(listToXml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=========================map 转 xml==========================");
        try {
            Map<String, Object> map = new HashMap<>();
            Student student = new Student();
            student.setAddress("china");
            student.setEmail("tom@125.com");
            student.setId(2);
            student.setName("tom");
            map.put("student", student);
            Birthday birthday = new Birthday("2010-11-22");
            map.put("birthday", birthday);
            Map<String, Class<?>> classMap = new HashMap<>();
            classMap.put("student", Student.class);
            classMap.put("birthday", Birthday.class);
            String mapToXml = XStreamUtils.listMapToXml(map, classMap, "student", "birthday");
            System.out.println(mapToXml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=========================xml 转 JavaBean==========================");
        String xml =
                "<user id='1'>" +
                        "<name>Test</name>" +
                        "<sex>1</sex>" +
                        "<roles>" +
                        "<id>1</id>" +
                        "<name>角色1</name>" +
                        "</roles>" +
                        "<roles>" +
                        "<id>2</id>" +
                        "<name>角色2</name>" +
                        "</roles>" +
                        "</user>";
        User user = XStreamUtils.xmlToBean(xml, User.class);
        System.out.println(user.getId() + "--" + user.getName());
        List<Role> roles = user.getRoles();
        for (Role r : roles) {
            System.out.println(r.getName());
        }

        System.out.println("=========================JavaBean 转 xml(以json格式类型输出）==========================");
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("User", User.class);
        classMap.put("Role", Role.class);
        String xmlToJson = XStreamUtils.xmlToJson(user, classMap, "User", "Role");
        System.out.println(xmlToJson);
    }
}
