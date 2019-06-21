package com.springboot.demo.actuator;

import com.springboot.demo.service.HhyUserService;
import org.apache.tomcat.util.net.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义端点
 * @author hehaiyang
 */

@ConfigurationProperties(prefix = "endpoints.userEndPoints")
public class HhyUserEndPoint extends AbstractEndpoint<Map<String,Object>> {

    @Resource
    private HhyUserService hhyUserService;

    public HhyUserEndPoint() {
        super("userEndPoints");
    }

    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> map = new HashMap<String, Object>();
        //当前时间
        map.put("current_time",new Date());
        //用户总数量
        map.put("user_num",hhyUserService.findUserTotalNum());
        return map;
    }
}

