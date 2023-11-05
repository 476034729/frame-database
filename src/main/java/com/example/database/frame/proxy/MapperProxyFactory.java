package com.example.database.frame.proxy;

import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession, Map<String, MapperData> mappers) {
        final MapperProxy mapperProxy = new MapperProxy(mappers, sqlSession);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
