package com.example.database.frame.proxy;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.mapper.MapperMethod;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;
    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession, Configuration configuration) {
        final MapperProxy mapperProxy = new MapperProxy(configuration, sqlSession, methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
