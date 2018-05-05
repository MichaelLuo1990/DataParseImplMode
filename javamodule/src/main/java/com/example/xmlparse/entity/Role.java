package com.example.xmlparse.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Desc 使用XStream注解方式实体类
 * Created by Michael on 2018/4/23.
 */

@XStreamAlias("role")
public class Role {

    private Integer id;
    private String name;
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
}
