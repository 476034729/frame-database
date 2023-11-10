package com.example.database.frame.session;

import com.example.database.frame.mapper.MapperData;

import java.util.List;

public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     *
     * @param tClass mapper接口的Class对象
     * @param <T>                  泛型
     * @return mapper接口的代理实例
     */
    <T> T getMapper(Class<T> tClass);

    <T> List<T> selectList(String statementKey, Object parameters) throws Exception;

    <T> T selectOne(String statementKey, Object parameters);
    int insert(String statementKey, Object parameters);

    int update(String statementKey, Object parameters);

    int delete(String statementKey, Object parameters);

}
