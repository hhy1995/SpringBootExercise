package com.springboot.demo.api;

import com.springboot.demo.domain.HhyUser;

/**
 * 用户接口
 */
public interface HhyUserDubboService {
    HhyUser findByUserNameAndPassword(String name, String password);
}
