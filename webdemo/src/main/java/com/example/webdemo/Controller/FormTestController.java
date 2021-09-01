package com.example.webdemo.Controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.origin.Origin;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/*
*   文件上传测试
* */
@Slf4j
@Controller
public class FormTestController {

    @GetMapping("/form_layouts.html")
    public String form_layouts(){
        return "form/form_layouts";
    }

    /**
     *  MultipartFile 自动封装上传过来的文件
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @SneakyThrows
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos){


            log.info("上传的信息：email={},username={},headerImg={},photos={}",email,username,
                    headerImg.getSize(),photos.length);

            if(!headerImg.isEmpty()){
            //    保存到文件服务器，oss服务器
                String originalFilename = headerImg.getOriginalFilename();
                headerImg.transferTo(new File("H:\\cache\\"+originalFilename));
            }

            if(photos.length >0){
                for(MultipartFile photo :photos){
                    if (  !photo.isEmpty()) {
                        String originalFilename = photo.getOriginalFilename();
                        photo.transferTo(new File("H:\\cache\\"+originalFilename));
                    }
                }
            }

        return  "main";
    }

}
