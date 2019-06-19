package com.springboot.demo.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 Druid的监控功能
 *
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //ServletRegistrationBean提供注册类
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //登录查看信息的账户和密码
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return  servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加需要忽略的各式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
