package com.example.database.frame.session;

import com.example.database.frame.config.Configuration;

public class SqlSessionFactoryBuilder {
  /**
   * 根据参数的字节输入流构建一个SqlSessionFactory工厂
   * @return SqlSessionFactory
   */
  public SqlSessionFactory build(Configuration configuration) {
    return new DefaultSqlSessionFactory(configuration);
  }
}
