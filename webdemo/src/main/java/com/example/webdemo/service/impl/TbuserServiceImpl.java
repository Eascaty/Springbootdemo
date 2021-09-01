package com.example.webdemo.service.impl;


import com.example.webdemo.bean.Tbuser;
import com.example.webdemo.mapper.TbuserMapper;
import com.example.webdemo.service.TbuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class TbuserServiceImpl implements TbuserService {
    @Autowired
    TbuserMapper tbuserMapper;

    public Tbuser getT(BigInteger id){

        return     tbuserMapper.getT(id);
    }


}
