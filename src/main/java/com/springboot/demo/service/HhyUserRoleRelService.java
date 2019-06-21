package com.springboot.demo.service;

import com.springboot.demo.model.HhyUserRoleRel;

import java.util.List;

/**
 * 用户角色关联Service
 */
public interface HhyUserRoleRelService {
    List<HhyUserRoleRel> findByUserId(String userId);
}
