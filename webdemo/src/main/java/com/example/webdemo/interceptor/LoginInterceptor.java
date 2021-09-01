package com.example.webdemo.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录检查
 * 1.配置好拦截器要拦截哪些请求
 * 2.把这些配置放在容器中
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handle
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handle) throws IOException, ServletException {

       String requestURI=request.getRequestURI();
       log.info("preHandle拦截的请求是{}",requestURI);


        //登陆检查逻辑
        HttpSession session=request.getSession();

        Object loginUser=session.getAttribute("loginUser");

        if(loginUser != null){
            //放行
            return  true;
        }
        //拦截住,未登录
        //response.sendRedirect("/");
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/").forward(request,response);
        return  false;

  }

    /**
     * 目标方法执行完成以后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle拦截的请求是{}",modelAndView);
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion执行的异常是{}",ex);
    }
}
