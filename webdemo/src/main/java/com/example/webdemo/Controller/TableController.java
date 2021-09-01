package com.example.webdemo.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webdemo.bean.User;
import com.example.webdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TableController {

    @Autowired
    UserService userService;

    @GetMapping("/basic_table.html")
    public String basic_table() {


        return "table/basic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, @RequestParam(value = "pn",defaultValue = "1")Integer pn, RedirectAttributes ra){


        userService.removeById(id);

        ra.addAttribute("pn",pn);

        return "redirect:/dynamic_table.html";
    }










    @GetMapping("/dynamic_table.html")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model) {


        List<User> list = userService.list();

        //model.addAttribute("users",list);

        ////分页查询数据
        Page<User> userPage = new Page<>(pn, 2);
        //
        ////分页查询的结果
        Page<User> page = userService.page(userPage, null);
        long current = page.getCurrent();
        long pages = page.getPages();
        long total = page.getTotal();
        page.getRecords();

        model.addAttribute("page",page);


        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table.html")
    public String responsive_table() {

        return "table/responsive_table";
    }

    @GetMapping("/editable_table.html")
    public String editable_table() {

        return "table/editable_table";
    }
}
