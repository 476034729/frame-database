package com.example.database.frame.session;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.config.DataSource;
import com.example.database.frame.handler.excutor.Executor;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.proxy.MapperProxy;
import com.example.database.frame.proxy.MapperProxyFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private final Connection connection;

    private final Executor executor;

    // 构造方法
    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
        connection = getConnection();
    }

    private Connection getConnection() {
        DataSource dataSource = configuration.getDataSource();
        try {
            Class.forName(dataSource.getDriver());
            return DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于创建代理对象
     *
     * @param tClass mapper接口的Class对象
     * @param <T>                  泛型
     * @return mapper接口的代理对象
     */
    @Override
    public <T> T getMapper(Class<T> tClass) {
        // 动态代理
        return new MapperProxyFactory<>(tClass).newInstance(this, configuration.getMappers());
    }

    @Override
    public <T> List<T> selectList(MapperData mapperData, Object parameter) {
        // TODO: 2023/11/4  parameter 暂时没用
        return executor.queryList(mapperData, connection);
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
