package com.example.webdemo.Controller;

import com.example.webdemo.bean.City;
import com.example.webdemo.bean.Tbuser;
import com.example.webdemo.bean.User;

import com.example.webdemo.service.CityService;
import com.example.webdemo.service.TbuserService;
import com.example.webdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.math.BigInteger;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TbuserService tbuserService;

    @Autowired
    CityService cityService;

    @Autowired
    StringRedisTemplate redisTemplate;


    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Long id){
       return cityService.getById(id);
    }

    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city){
        cityService.saveCity(city);
        return  city;
    }




    @ResponseBody
    @GetMapping("/getT")
    public Tbuser getT(@RequestParam("id") BigInteger id) {


        return tbuserService.getT(id);
    }

    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from invoice_items", Long.class);
        return aLong.toString();
    }
    /*
     *
     *来登录页
     * */

    @GetMapping(value = {"/", "/login"})
    public String loginpage() {
        return "login";
    }


    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {

        if (StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())) {
            //把登陆成功的用户保存起来
            session.setAttribute("loginUser", user);
            //登录成功重定向到main.html;  重定向防止表单重复提交
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "账号密码错误");
            //回到登录页面
            return "login";
        }

    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model) {

        log.info("当前方法是：{}", "mainPage");
        //Object loginUser =  session.getAttribute("loginUser");
        //if(loginUser !=null){
        //    return "main";
        //}else{
        //
        //    model.addAttribute("msg","请重新登陆");
        //    return "login";
        //}
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String s = opsForValue.get("/main.html");
        String s1 = opsForValue.get("/sql");

        model.addAttribute("mainCount",s);
        model.addAttribute("sqlCount",s1);

        return "main";


    }


}
