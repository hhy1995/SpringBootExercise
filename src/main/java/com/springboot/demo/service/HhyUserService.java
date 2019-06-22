package com.springboot.demo.service;

import com.springboot.demo.model.HhyMood;
import com.springboot.demo.model.HhyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 用户服务层的接口
 * @author hehaiyang
 */
public interface HhyUserService {
    HhyUser findUserById(String id);
    List<HhyUser> findAll();
    HhyUser save(HhyUser hhyUser);
    void delete(String id);
    //分页
    Page<HhyUser> findAll(Pageable pageable);
    //自定义的一些查询方法
    List<HhyUser> findByName(String name);
    HhyUser findByUserName(String name);
    List<HhyUser> findByNameLike(String name);
    List<HhyUser> findByIdIn(Collection<String> ids);

    HhyUser findUserByNameAndPassword(String name,String password);

    Future<List<HhyUser>> findAsynAll();
    HhyUser findByNameAndPasswordRetry(String name,String password);

    Long findUserTotalNum();
}
