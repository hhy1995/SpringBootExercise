package com.springboot.demo.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义健康类
 * @author hehaiyang
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        Long totalSpace = checkTocalSpace();
        Long free = checkFree();
        String status = checkStatus();
        checkFree();
        return new Health.Builder()
                .up()
                .withDetail("status",status)
                .withDetail("total",totalSpace)
                .withDetail("free",free)
                .build();
    }
    private String checkStatus(){
        //结合真实项目，获取相关参数
        return "UP";
    }
    private Long checkTocalSpace(){
        //结合真实项目，获取相关参数
        return 10000L;
    }
    private Long checkFree(){
        //结合真实项目，获取相关参数
        return 5000L;
    }
}
