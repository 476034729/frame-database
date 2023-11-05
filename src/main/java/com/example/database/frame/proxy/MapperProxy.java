package com.example.database.frame.proxy;

import com.example.database.frame.constants.FrameConstants;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

// 自定义MapperProxyFactory类
public class MapperProxy implements InvocationHandler {
    // mappers集合
    private final Map<String, MapperData> mappers;

    private final SqlSession sqlSession;

    public MapperProxy(Map<String, MapperData> mappers, SqlSession sqlSession) {
        this.mappers = mappers;
        this.sqlSession = sqlSession;
    }

    // 实现InvocationHandler接口的invoke()方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String key = className + FrameConstants.HEAD_LINE + methodName;
        MapperData mapperData = mappers.get(key);
        if (mapperData == null) {
            throw new IllegalArgumentException("传入参数有误");
        }
        return sqlSession.selectList(mapperData, args);

    }
}
