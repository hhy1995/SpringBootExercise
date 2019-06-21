package com.springboot.demo.repository;

import com.springboot.demo.model.HhyRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户角色Repository
 * @author hehaiyang
 */
public interface HhyRoleRepository extends JpaRepository<HhyRole,String> {

}
