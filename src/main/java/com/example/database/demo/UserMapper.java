package com.example.database.demo;

import com.example.database.demo.entity.User;

import java.util.List;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public interface UserMapper {

    List<User> queryList();
}
