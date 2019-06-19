package com.springboot.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 */
@WebFilter(filterName = "hhyUserFilter",urlPatterns = "/*")
public class HhyUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------Filter 初始化（init）------");
    }

    @Override
    public void destroy() {
        System.out.println("------Filter 销毁（destroy）------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------执行 Filter（doFilter）------");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
