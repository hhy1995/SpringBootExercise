package com.springboot.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security 配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //将CustomUserService装入Spring容器
    @Bean
    public CustomUserService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //路由策略和访问权限的简单配置
        http
                .formLogin()                      //启用默认登陆页面
                .failureUrl("/login?error")     //登陆失败返回URL:/login?error
                .defaultSuccessUrl("/hhyUser/test")  //登陆成功跳转URL，这里跳转到用户首页
                .permitAll();                    //登陆页面全部权限可访问
        super.configure(http);
    }
    /**
     * 配置内存用户
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
//            .inMemoryAuthentication()
//            .withUser("hhy").password("hhy").roles("ADMIN")
//            .and()
//            .withUser("lalala").password("lalala").roles("USER");
    }
}
