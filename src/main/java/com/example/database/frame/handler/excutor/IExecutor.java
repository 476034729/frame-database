package com.example.database.frame.handler.excutor;

import com.example.database.frame.mapper.MapperData;

import java.util.List;

/**
 * @author tz
 * @createDate 2023-11-10
 */
public interface IExecutor {

    <T> List<T> selectList(MapperData mapperData, Object params);

    void close();
}
