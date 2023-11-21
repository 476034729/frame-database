package com.example.database.frame.handler.excutor;

import com.example.database.frame.mapper.MapperData;

import java.util.List;
import java.util.Map;

/**
 * @author tz
 * @createDate 2023-11-10
 */
public interface IExecutor {

    <T> List<T> selectList(MapperData mapperData, Map<String,Object> params,Boolean ifXml);
    <T> T selectOne(MapperData mapperData, Map<String,Object> params,Boolean ifXml);

    void close();

    int insert(MapperData mapperData, Object[] params);
    int delete(MapperData mapperData, Object[] params,Boolean ifXml);
}
