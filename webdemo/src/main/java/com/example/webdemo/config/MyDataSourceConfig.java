package com.example.webdemo.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Deprecated
//@Configuration
public class MyDataSourceConfig {
    //@Bean
    //@ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();

        //druidDataSource.setFilters("stat,wall");
        //
        //druidDataSource.setMaxActive(10);
         return  druidDataSource;

        }

    /**
     * 配置 druid的监控页功能
     * @return
     */
    @Bean
        public ServletRegistrationBean staViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");

        registrationBean.addInitParameter("loginUsername","admin");
        registrationBean.addInitParameter("loginPassword","123456");


        return  registrationBean;
        }
    //
    ///**
    // * WebStatFilter  用于采集web-jdbc关联监控的数据
    // */
    //    @Bean
    //    public FilterRegistrationBean WebStatFilter(){
    //        WebStatFilter webStatFilter = new WebStatFilter();
    //        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
    //        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
    //        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    //
    //        return  filterRegistrationBean;
    //    }
}
