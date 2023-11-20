package com.example.database.demo.mapper;

import com.example.database.demo.entity.User;
import com.example.database.frame.annotation.Mapper;
import com.example.database.frame.annotation.ParamBody;
import com.example.database.frame.mapper.BaseMapper;

import java.util.List;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> queryList(String id,String name,String address);
    User queryById(Long id);

}
