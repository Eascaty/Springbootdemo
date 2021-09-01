package com.example.webdemo.mapper;

import com.example.webdemo.bean.Tbuser;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface TbuserMapper {
    public Tbuser getT(BigInteger id);

}
