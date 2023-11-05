package com.example.database.frame.util;

import java.io.InputStream;

public class Resources {

  /**
   * 获取配置文件并转换为输入流
   * @param filePath 配置文件路径
   * @return 配置文件输入流
   */
  public static InputStream getResourcesAsStream(String filePath) {
    return Resources.class.getClassLoader().getResourceAsStream(filePath);
  }

  public static Class<?> getClassName(String className) throws ClassNotFoundException {
    return Class.forName(className);
  }
}
