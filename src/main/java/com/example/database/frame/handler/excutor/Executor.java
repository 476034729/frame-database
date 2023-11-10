package com.example.database.frame.handler.excutor;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.config.DataSource;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 自定义Executor类
public class Executor implements IExecutor{
    private final Configuration configuration;
    private final Connection connection;

    public Executor(Configuration configuration) {
        this.configuration = configuration;
        this.connection=getConnection();
    }

    /**
     * selectList()方法
     *
     * @param mapperData mapper接口
     * @param <T>        泛型
     * @return 结果
     */
    @Override
    public <T> List<T> selectList(MapperData mapperData,Object params) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 取出SQL语句
            String queryString = mapperData.getSql();
            // 取出结果类型
            Class<?> resultClazz = mapperData.getResultType();
            // 获取PreparedStatement对象并执行
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            // 从结果集对象封装结果
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                //实例化要封装的实体类对象
                T obj = (T) resultClazz.getConstructor().newInstance();
                // 取出结果集的元信息
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                // 取出总列数
                int columnCount = resultSetMetaData.getColumnCount();
                // 遍历总列数给对象赋值
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(columnName);
                    columnName = StringUtils.convertToCamelCase(columnName);
                    columnValue=castFieldType(columnValue);
                    PropertyDescriptor descriptor = new PropertyDescriptor(columnName, resultClazz);
                    Method writeMethod = descriptor.getWriteMethod();
                    writeMethod.invoke(obj, columnValue);
                }
                // 把赋好值的对象加入到集合中
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 调用release()方法释放资源
            release(preparedStatement, resultSet);
        }
    }

    private Object castFieldType(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj ? 1 : 0;
        }
        return obj;
    }

    /**
     * 释放资源
     *
     * @param preparedStatement preparedStatement对象
     * @param resultSet         resultSet对象
     */
    private void release(PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
