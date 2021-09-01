package com.example.webdemo.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Tbuser {
        private BigInteger user_id;
        private String username;
        private String mobile;
        private String password;
        private Date creat_time;


}
