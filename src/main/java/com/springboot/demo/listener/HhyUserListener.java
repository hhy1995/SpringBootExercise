package com.springboot.demo.listener;

import com.springboot.demo.model.HhyUser;
import com.springboot.demo.service.HhyUserService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * 监听器
 * @author hehaiyang
 */
@WebListener
public class HhyUserListener implements ServletContextListener {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private HhyUserService hhyUserService;
    private static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //查询数据库的所有的用户
        List<HhyUser> hhyUserList = hhyUserService.findAll();
        //清除所有的记录
        redisTemplate.delete(ALL_USER);
        //将数据放在redis缓存当中
        redisTemplate.opsForList().leftPushAll(ALL_USER,hhyUserList);
        //查询所有的用户数据
        List<HhyUser> queryUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        System.out.println("缓存当中目前存在的用户数有： " + queryUserList.size());
        System.out.println("ServletContext 上下文初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext 上下文销毁");
    }
}
