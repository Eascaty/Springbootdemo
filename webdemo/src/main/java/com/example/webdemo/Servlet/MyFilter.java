package com.example.webdemo.Servlet;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.xml.ws.Response;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = {"/css/*","/images/*"})
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            log.info("MyFilter初始化完成");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
            log.info("MyFilter工作");
        chain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
            log.info("MyFilter销毁");
    }
}
