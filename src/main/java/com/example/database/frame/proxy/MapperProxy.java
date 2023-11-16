package com.example.database.frame.proxy;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.constants.FrameConstants;
import com.example.database.frame.exception.DataBaseFrameException;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.mapper.MapperMethod;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.zip.DataFormatException;

// 自定义MapperProxyFactory类
public class MapperProxy implements InvocationHandler {
    private final SqlSession sqlSession;
    private final Configuration configuration;
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(Configuration configuration, SqlSession sqlSession, Map<Method, MapperMethod> methodCache) {
        this.configuration = configuration;
        this.sqlSession = sqlSession;
        this.methodCache = methodCache;
    }

    // 实现InvocationHandler接口的invoke()方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String key = className + FrameConstants.HEAD_LINE + methodName;
        MapperData mapperData =configuration.getMapperData(key);
        if (mapperData == null) {
            throw new DataBaseFrameException("传入参数有误");
        }
        MapperMethod mapperMethod = this.cachedMapperMethod(method,mapperData,key);
        return mapperMethod.execute(sqlSession,args);
    }

    private MapperMethod cachedMapperMethod(Method method,MapperData mapperData,String key) {
        MapperMethod mapperMethod =this.methodCache.get(method);
        if (mapperMethod == null) {
            mapperMethod = new MapperMethod(mapperData.getSqlCommandType(),method, key);
            this.methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
