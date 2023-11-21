package com.example.database.frame.session;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.handler.excutor.Executor;
import com.example.database.frame.handler.excutor.IExecutor;
import com.example.database.frame.proxy.MapperProxyFactory;


import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private final IExecutor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new Executor(configuration);
    }


    /**
     * 用于创建代理对象
     *
     * @param tClass mapper接口的Class对象
     * @param <T>    泛型
     * @return mapper接口的代理对象
     */
    @Override
    public <T> T getMapper(Class<T> tClass) {
        // 动态代理
        return new MapperProxyFactory<>(tClass).newInstance(this, configuration);
    }

    @Override
    public <T> List<T> selectList(String statementKey, Map<String,Object> parameters,Boolean ifXml) {
        return executor.selectList(configuration.getMapperData(statementKey), parameters,ifXml);
    }

    @Override
    public <T> T selectOne(String statementKey, Map<String,Object> parameters,Boolean ifXml) {
        return executor.selectOne(configuration.getMapperData(statementKey), parameters,ifXml);
    }

    @Override
    public <T> T selectById(String statementKey, Object param) {
        return null;
    }

    @Override
    public int insert(String statementKey, Object[] params) {
        return executor.insert(configuration.getMapperData(statementKey),params);
    }

    @Override
    public int update(String statementKey, Object parameters) {
        return 0;
    }

    @Override
    public int deleteById(String statementKey, Object param) {
        return 0;
    }

    @Override
    public int delete(String statementKey, Map<String, Object> parameters, Boolean ifXml) {
        return 0;
    }


}
