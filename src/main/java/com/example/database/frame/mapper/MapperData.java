package com.example.database.frame.mapper;

import com.example.database.frame.enums.SqlCommandType;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.Method;
import java.util.*;

public class MapperData {
    private String sql;
    private SqlCommandType sqlCommandType;
    private Class<?> resultType;
    private Class<?> paramType;
    private boolean ifReturnMany;
    private boolean ifReturnVoid;
    private SortedMap<Integer, String> params;
    private final boolean hasNamedParameters;

    public MapperData(String sql, Class<?> resultType, Class<?> paramType, SqlCommandType sqlCommandType) {
        this.sql = sql;
        this.sqlCommandType = sqlCommandType;
        this.resultType = resultType;
        this.paramType = paramType;
        this.ifReturnMany = Collection.class.isAssignableFrom(resultType);
        this.ifReturnVoid = Void.TYPE.equals(resultType);
        this.hasNamedParameters = false;
    }

    public void setParams(SortedMap<Integer, String> params) {
        this.params = Collections.unmodifiableSortedMap(params);
    }

    public SortedMap<Integer, String> getParams() {
        return params;
    }

    public boolean isHasNamedParameters() {
        return hasNamedParameters;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public String getSql() {
        return sql;
    }

    public Class<?> getParamType() {
        return paramType;
    }

    public void setParamType(Class<?> paramType) {
        this.paramType = paramType;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public boolean isIfReturnMany() {
        return ifReturnMany;
    }

    public void setIfReturnMany(boolean ifReturnMany) {
        this.ifReturnMany = ifReturnMany;
    }

    public boolean isIfReturnVoid() {
        return ifReturnVoid;
    }

    public void setIfReturnVoid(boolean ifReturnVoid) {
        this.ifReturnVoid = ifReturnVoid;
    }

    public SortedMap<Integer, String> getParams(Method method, boolean hasNamedParameters) {
        SortedMap<Integer, String> params = new TreeMap();
        Class<?>[] argTypes = method.getParameterTypes();
        for (int i = 0; i < argTypes.length; ++i) {
            String paramName = String.valueOf(params.size());
            params.put(i, paramName);
        }
        return params;
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object param;
        Object result = null;
        if (SqlCommandType.INSERT.equals(this.getSqlCommandType())) {
            param = this.convertArgsToSqlCommandParam(args);
        } else if (SqlCommandType.UPDATE.equals(this.getSqlCommandType())) {
            param = this.convertArgsToSqlCommandParam(args);
        } else if (SqlCommandType.DELETE.equals(this.getSqlCommandType())) {
            param = this.convertArgsToSqlCommandParam(args);
        } else {
            if (this.isIfReturnVoid()) {
                result = null;
            } else if (this.isIfReturnMany()) {
            } else {
                param = this.convertArgsToSqlCommandParam(args);
            }
        }
        return result;
    }


    public Object convertArgsToSqlCommandParam(Object[] args) {
        int paramCount = this.params.size();
        if (args == null || paramCount == 0) {
            return null;
        }
        if (!this.hasNamedParameters && paramCount == 1) {
            return args[this.params.keySet().iterator().next()];
        } else {
            Map<String, Object> param = new HashMap<>();
            int i = 0;

            for (Iterator<Map.Entry<Integer, String>> it = this.params.entrySet().iterator(); it.hasNext(); ++i) {
                Map.Entry<Integer, String> entry = it.next();
                param.put(entry.getValue(), args[entry.getKey()]);
                String genericParamName = "param" + (i + 1);
                if (!param.containsKey(genericParamName)) {
                    param.put(genericParamName, args[entry.getKey()]);
                }
            }
            return param;
        }
    }
}
