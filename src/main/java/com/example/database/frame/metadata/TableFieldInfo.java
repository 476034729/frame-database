package com.example.database.frame.metadata;

import com.example.database.frame.util.SqlScriptUtils;

import java.lang.reflect.Field;

/**
 * @author tz
 * @createDate 2023-11-16
 */
public class TableFieldInfo {

    /**
     * 属性
     *
     * @since 3.3.1
     */
    private final Field field;
    /**
     * 字段名
     */
    private final String column;
    private final String el;

    /**
     * 属性名
     */
    private final String property;
    /**
     * 属性类型
     */
    private final Class<?> propertyType;

    public TableFieldInfo(Field field, String column, String property, Class<?> propertyType) {
        this.field = field;
        this.column = column;
        this.property = property;
        this.propertyType = propertyType;
        this.el = this.property;
    }

    public String getEl() {
        return el;
    }

    public Field getField() {
        return field;
    }

    public String getColumn() {
        return column;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getPropertyType() {
        return propertyType;
    }


    public String getInsertSqlColumnMaybeIf(final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return this.getInsertSqlColumn();
    }
    public String getInsertSqlColumn() {
        return this.column + ",";
    }
    public String getInsertSqlPropertyMaybeIf(final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return this.getInsertSqlProperty(newPrefix);
    }

    public String getInsertSqlProperty(final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return SqlScriptUtils.safeParam(newPrefix + this.el) + ",";
    }
}
