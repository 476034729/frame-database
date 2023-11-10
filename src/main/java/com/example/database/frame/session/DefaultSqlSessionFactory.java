package com.example.database.frame.session;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.handler.excutor.Executor;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 用于创建一个新的操作数据库对象
     *
     * @return SqlSession
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
