package com.springboot.demo.service.impl;

import com.springboot.demo.model.HhyUserRoleRel;
import com.springboot.demo.repository.HhyUserRoleRelRepository;
import com.springboot.demo.service.HhyUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 用户角色关联Service实现类
 * @author hehaiyang
 */
@Service
public class HhyUserRoleRelServiceImpl implements HhyUserRoleRelService {

    @Resource
    private HhyUserRoleRelRepository hhyUserRoleRelRepository;

    @Override
    public List<HhyUserRoleRel> findByUserId(String userId) {
        return hhyUserRoleRelRepository.findByUserId(userId);
    }
}
