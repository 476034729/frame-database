package com.example.database.frame.handler;




import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ResultHandler {
    private Logger logger = LoggerFactory.getLogger(ResultHandler.class);

    private Class<?> type;
    private ResultSet resultSet;

    public ResultHandler(Class<?> type, ResultSet resultSet){
        this.type = type;
        this.resultSet = resultSet;
    }

    public ResultHandler(){
    }

    public <E> E handle() {
        try{
            Constructor<?> constructor = type.getConstructor(); // 获取无参数构造函数
            Object resultObject = constructor.newInstance();
            if(resultSet.next()){
                for(Field field:resultObject.getClass().getDeclaredFields()){
                    setValue(resultObject,field,resultSet);
                }
            }
            return (E) resultObject;
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException happen when processing in ResultHandler:",e);
        } catch (SQLException e) {
            logger.error("SQLException happen when processing in ResultHandler:",e);
        } catch (NoSuchMethodException e) {
            logger.error("NoSuchMethodException happen when processing in ResultHandler:",e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException happen when processing in ResultHandler:",e);
        }catch (Exception e) {
            logger.error("error is ",e);
        }finally {
            try {
                if(resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setValue( Object resultObject,Field field,ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Method method = type.getMethod("set"+upperCapital(field.getName()),field.getType());
        method.invoke(resultObject,getResult(field,resultSet));
    }

    public Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }else if(String.class == type){
            return rs.getString(field.getName());
        }else if(Long.class == type){
            return rs.getLong(field.getName());
        }else if(Date.class == type){
            return rs.getDate(field.getName());
        }else{
            return rs.getString(field.getName());
        }
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }

}
