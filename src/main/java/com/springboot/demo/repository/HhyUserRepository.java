package com.springboot.demo.repository;

import com.springboot.demo.model.HhyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * 用户的Repository
 * @author hehaiyang
 */
public interface HhyUserRepository extends JpaRepository<HhyUser,String> {
    /**
     * @param name 通过姓名查询用户
     * @return
     * 等价于：select u from hhy_user u where u.name = ?
     */
    List<HhyUser> findByName(String name);

    /**
     * @param name 通过姓名进行模糊查询，参数为name
     * @return
     * 等价于：select u from hhy_user u where u.name like ?
     */
    List<HhyUser> findByNameLike(String name);

    /**
     * @param ids 通过主键id集合进行查询，参数为id集合
     * @return
     */
    List<HhyUser> findByIdIn(Collection<String> ids);
}
