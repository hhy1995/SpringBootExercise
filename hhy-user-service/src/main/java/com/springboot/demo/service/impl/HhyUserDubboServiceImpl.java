package com.springboot.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.demo.api.HhyUserDubboService;
import com.springboot.demo.domain.HhyUser;

/**
 * 对外提供用户服务类
 * @author hehaiyang
 */
@Service(version = "1.0")
public class HhyUserDubboServiceImpl implements HhyUserDubboService {
    @Override
    public HhyUser findByUserNameAndPassword(String name, String password) {
        //连接数据库，查询用户数据，此处省略
        HhyUser hhyUser = new HhyUser();
        hhyUser.setName("hhy");
        hhyUser.setPassword("hhy");
        return hhyUser;
    }
}
