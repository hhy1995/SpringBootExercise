package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication  //是一个组合注解,效果和下面三个注解结合起来一致
@ServletComponentScan
//@EnableAutoConfiguration
//@ComponentScan
//@SpringBootConfiguration

//执行这里，重新启动SpringBoot项目
public class MySpringBootApplication{

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}
