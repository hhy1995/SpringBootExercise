package com.springboot.demo.repository;

import com.springboot.demo.model.HhyUserRoleRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户角色关联Repository
 * @author hehaiyang
 */

public interface HhyUserRoleRelRepository extends JpaRepository<HhyUserRoleRel,String> {
    List<HhyUserRoleRel> findByUserId(@Param("userId")String userID);
}
