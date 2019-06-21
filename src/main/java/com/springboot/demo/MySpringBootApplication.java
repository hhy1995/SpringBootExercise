package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


//@EnableAutoConfiguration
//@ComponentScan
//@SpringBootConfiguration

//执行这里，重新启动SpringBoot项目(注意：启动项目之前，首先启动Redis服务器，否则会报错)
@SpringBootApplication  //是一个组合注解,效果和下面三个注解结合起来一致
@ServletComponentScan
@ImportResource(locations = {"classpath:spring-mvc.xml"})    //导入资源配置文件，可以让Spring Boot读取到
@EnableAsync  //开启异步调用
@EnableRetry  //开启retry重试
public class MySpringBootApplication{

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}
