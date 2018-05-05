package com.example.xmlparse.entity;

import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.basic.DateConverter;

/**
 * Desc 使用XStream注解方式实体类
 *
 * @XStreamAlias 定义别名
 * @XStreamAsAttribute 定义为属性
 * @XStreamOmitField 忽略
 * @XStreamConverter 处理日期格式
 * @XStreamImplicit(itemFieldName = "roles") 处理List
 * Created by Michael on 2018/4/23.
 */

@XStreamAlias("user")
public class User {

    @XStreamAsAttribute
    private Integer id;
    private String name;
    @XStreamOmitField
    private int age;
    private String sex;
    @XStreamConverter(value = DateConverter.class)
    private Date birthday = new Date();
    @XStreamImplicit(itemFieldName = "roles")
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
