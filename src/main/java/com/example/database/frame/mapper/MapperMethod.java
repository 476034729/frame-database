package com.example.database.frame.mapper;

import com.example.database.frame.annotation.ParamBody;
import com.example.database.frame.enums.SqlCommandType;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tz
 * @createDate 2023-11-10
 */
public class MapperMethod {

    private final SqlCommandType sqlCommandType;
    private final boolean ifReturnMany;
    private final boolean ifReturnVoid;
    private final List<String> params;
    private final boolean hasParamBody;
    private final String key;

    public MapperMethod(SqlCommandType sqlCommandType, Method method, String key) {
        this.sqlCommandType = sqlCommandType;
        this.ifReturnMany = Collection.class.isAssignableFrom(method.getReturnType());
        this.ifReturnVoid = Void.TYPE.equals(method.getReturnType());
        this.params = this.getParams(method);
        this.hasParamBody = isHasParamBody(method);
        this.key = key;
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
        Map<String, Object> param;
        Object result = null;
        if (SqlCommandType.INSERT.equals(this.getSqlCommandType())) {
            param = this.convertArgsToSqlCommandParam(args);
        } else if (SqlCommandType.UPDATE.equals(this.getSqlCommandType())) {
            param = this.convertArgsToSqlCommandParam(args);
        } else if (SqlCommandType.DELETE.equals(this.getSqlCommandType())) {
            param = this.convertArgsToSqlCommandParam(args);
        } else {
            param = this.convertArgsToSqlCommandParam(args);
            if (this.isIfReturnVoid()) {
                result = null;
            } else if (this.isIfReturnMany()) {
                result=sqlSession.selectList(key, param);
            } else {
                result=sqlSession.selectOne(key, param);
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
            Map<String, Object> map =new HashMap<>(paramCount);
            for (int i=0;i<paramCount;i++) {
                map.put(params.get(i), args[i]);
            }
            return map;
        }
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
