package com.example.webdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webdemo.bean.User;
import org.apache.ibatis.annotations.Mapper;


/**
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
