package com.springboot.demo.dao;

import com.springboot.demo.model.HhyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Dao
 * @author hehaiyang
 */
@Mapper
public interface HhyUserDao {
    /**
     * 通过用户名和密码查询用户
     * @param name
     * @param password
     * @return
     */
    HhyUser findByNameAndPassword(@Param("name") String name, @Param("password") String password);
    /**
     * 通过用户名查询用户
     * @param name
     */
    HhyUser findByUserName(@Param("name") String name);

}
