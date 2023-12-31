package com.example.database.frame.mapper;

import com.example.database.frame.enums.SqlCommandType;
import com.example.database.frame.enums.SqlType;
import com.example.database.frame.session.SqlSession;

import java.lang.reflect.Method;
import java.util.*;

public class MapperData {
    private String sql;
    private SqlCommandType sqlCommandType;
    private SqlType sqlType;
    private Class<?> resultType;
    private Class<?> paramType;



    public MapperData(String sql, Class<?> resultType, Class<?> paramType, SqlCommandType sqlCommandType,SqlType sqlType ) {
        this.sql = sql;
        this.sqlCommandType = sqlCommandType;
        this.resultType = resultType;
        this.paramType = paramType;
        this.sqlType = sqlType;
    }

    public SqlType getSqlType() {
        return sqlType;
    }

    public void setSqlType(SqlType sqlType) {
        this.sqlType = sqlType;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public String getSql() {
        return this.sql;
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


}
