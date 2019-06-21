package com.springboot.demo.actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 自定义端点配置类
 * @author hehaiyang
 */
@Configuration
public class EndPointConfig {

    @Bean
    public Endpoint<Map<String,Object>> HhyUserEndPoint(){
        return new HhyUserEndPoint();
    }
}
