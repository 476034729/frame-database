package com.example.database.frame.mapper;

import com.example.database.frame.annotation.ParamBody;
import com.example.database.frame.enums.SqlCommandType;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.enums.SqlType;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tz
 * @createDate 2023-11-10
 */
public class MapperMethod {

    private final SqlCommandType sqlCommandType;
    private final SqlType sqlType;
    private final boolean ifReturnMany;
    private final boolean ifReturnVoid;
    private final List<String> params;
    private final boolean hasParamBody;
    private final String key;
    private final String methodName;

    public MapperMethod(SqlCommandType sqlCommandType, SqlType sqlType, Method method, String key ) {
        this.sqlCommandType = sqlCommandType;
        this.sqlType = sqlType;
        this.ifReturnMany = Collection.class.isAssignableFrom(method.getReturnType());
        this.ifReturnVoid = Void.TYPE.equals(method.getReturnType());
        this.params = this.getParams(method);
        this.hasParamBody = isHasParamBody(method);
        this.key = key;
        this.methodName = method.getName();
    }

    public SqlType getSqlType() {
        return sqlType;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getKey() {
        return key;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public boolean isIfReturnMany() {
        return ifReturnMany;
    }

    public boolean isIfReturnVoid() {
        return ifReturnVoid;
    }

    public List<String> getParams() {
        return params;
    }

    public boolean isHasParamBody() {
        return hasParamBody;
    }

    public Object execute(SqlSession sqlSession, Object[] args) throws Exception {
        Object result = null;
        if (SqlCommandType.INSERT.equals(this.getSqlCommandType())) {
            Object[] param = this.convertArgsToSqlCommandParam(args[0]);
            result = sqlSession.insert(key, param);
        } else if (SqlCommandType.UPDATE.equals(this.getSqlCommandType())) {
            this.convertArgsToSqlCommandParam(args);
        } else if (SqlCommandType.DELETE.equals(this.getSqlCommandType())) {
            if(SqlMethod.DELETE_BY_ID.getMethod().equals(methodName)){
                result = sqlSession.deleteById(key, args[0]);
            }else {
                result = sqlSession.delete(key,this.convertArgsToSqlCommandParam(args),SqlType.XML.equals(sqlType));
            }
        } else {
            if (this.isIfReturnVoid()) {
                return null;
            } else if (this.isIfReturnMany()) {
                result = sqlSession.selectList(key, this.convertArgsToSqlCommandParam(args),SqlType.XML.equals(sqlType));
            } else if(SqlMethod.SELECT_BY_ID.getMethod().equals(methodName)){
                result = sqlSession.selectById(key, args[0]);
            }else {
                result = sqlSession.selectOne(key, this.convertArgsToSqlCommandParam(args),SqlType.XML.equals(sqlType));
            }
        }
        return result;
    }

    private List<String> getParams(Method method) {
        return Arrays.stream(method.getParameters())
                .map(Parameter::getName)
                .collect(Collectors.toList());
    }

    /**
     * 判断是否含有注解，每个参数可能多个注解
     *
     * @param method
     * @return
     */
    private boolean isHasParamBody(Method method) {
        Object[][] allParamAnnotations = method.getParameterAnnotations();
        return Arrays.stream(allParamAnnotations)
                .anyMatch(it -> Arrays.stream(it)
                        .anyMatch(anno -> anno instanceof ParamBody));
    }

    public Map<String, Object> convertArgsToSqlCommandParam(Object[] args) {
        int paramCount = this.params.size();
        if (args == null || paramCount == 0) {
            return null;
        }
        if (this.hasParamBody && paramCount == 1) {
            return objectToMap(args[0]);
        } else {
            Map<String, Object> map = new HashMap<>(paramCount);
            for (int i = 0; i < paramCount; i++) {
                map.put(params.get(i), args[i]);
            }
            return map;
        }
    }

    public Object[] convertArgsToSqlCommandParam(Object object) {
        // 使用反射获取对象的字段
        Field[] fields = object.getClass().getDeclaredFields();
        Object[] objects = new Object[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                // 将字段的名称作为参数名，字段的值作为参数值放入map中
                objects[i] = fields[i].get(object);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return objects;
    }


    private Map<String, Object> objectToMap(Object object) {
        // 使用反射获取对象的字段
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>(fields.length);
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                // 将字段的名称作为参数名，字段的值作为参数值放入map中
                map.put(field.getName(), field.get(object));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
