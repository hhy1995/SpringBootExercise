package com.springboot.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * mysql数据库测试表,实体类
 * @author hehaiyang
 */
@Entity  //每个持久化POJO类都是一个实体Bean,通过在类的定义当中使用该注解来进行声明
@Table(name = "hhy_user")  //声明此对象映射的数据库表
public class HhyUser implements Serializable {

    @Id //指定表的主键
    private String id;

    private String name;

    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "hhy_User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
