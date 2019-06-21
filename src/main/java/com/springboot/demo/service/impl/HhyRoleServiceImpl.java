package com.springboot.demo.service.impl;

import com.springboot.demo.model.HhyRole;
import com.springboot.demo.repository.HhyRoleRepository;
import com.springboot.demo.service.HhyRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户角色Service实现类
 * @author hehaiyang
 */
@Service
public class HhyRoleServiceImpl implements HhyRoleService {

    @Resource
    private HhyRoleRepository hhyRoleRepository;

    @Override
    public HhyRole find(String id) {
        return hhyRoleRepository.findById(id).get();
    }
}
