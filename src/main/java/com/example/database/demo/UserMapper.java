package com.example.database.demo;

import com.example.database.demo.entity.User;
import com.example.database.frame.annotation.ParamBody;

import java.util.List;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public interface UserMapper {

    List<User> queryList(String id,String name,String address);
    List<User> queryListById(@ParamBody("user") User user);
}
