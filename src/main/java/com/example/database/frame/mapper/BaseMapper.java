package com.example.database.frame.mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author TANGZHEN
 * @createDate 2023-11-17
 */
public interface BaseMapper<T> {

    int insert(T t);

    int updateById(Serializable id);

    int deleteById(Serializable id);

    T selectById(Serializable id);

    List<T> selectList();
}
