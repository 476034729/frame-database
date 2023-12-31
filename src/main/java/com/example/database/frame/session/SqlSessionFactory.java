package com.example.database.frame.session;

public interface SqlSessionFactory {
  /**
   * 用于打开一个新的SqlSession对象
   * @return SqlSession
   */
  SqlSession openSession();
}
