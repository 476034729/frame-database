package com.example.database.frame.session;

import com.example.database.frame.mapper.MapperData;

import java.util.List;
import java.util.Map;

public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     *
     * @param tClass mapper接口的Class对象
     * @param <T>                  泛型
     * @return mapper接口的代理实例
     */
    <T> T getMapper(Class<T> tClass);

    <T> List<T> selectList(String statementKey, Map<String,Object> parameters,Boolean ifXml) throws Exception;

    <T> T selectOne(String statementKey, Map<String,Object> parameters,Boolean ifXml);
    <T> T selectById(String statementKey, Object param);
    int insert(String statementKey, Object[] parameters);

    int update(String statementKey, Object parameters);

    int deleteById(String statementKey,Object param);
    int delete(String statementKey,Map<String,Object> parameters,Boolean ifXml);

}
