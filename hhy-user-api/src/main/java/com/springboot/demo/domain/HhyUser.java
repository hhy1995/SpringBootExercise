package com.springboot.demo.domain;

/**
 * 用户实体类
 */
public class HhyUser {
    //主键
    private String id;
    //用户名
    private String name;
    //密码
    private String password;
    //邮箱
    private String mail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
