package com.example.database.frame.metadata;

import com.example.database.frame.constants.FrameConstants;
import com.example.database.frame.util.SqlScriptUtils;
import com.example.database.frame.util.StringUtils;

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
    private final Boolean ifLast;
    private String sqlSelect;
    private boolean select;


    public TableFieldInfo(Field field, String column, String property, Class<?> propertyType, Boolean ifLast) {
        this.field = field;
        this.column = column;
        this.property = property;
        this.propertyType = propertyType;
        this.ifLast = ifLast;
        this.el = this.property;
        this.select = true;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getSqlSelect() {
        return sqlSelect;
    }

    public void setSqlSelect(String sqlSelect) {
        this.sqlSelect = sqlSelect;
    }

    public Boolean getIfLast() {
        return ifLast;
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


    public String getInsertSqlColumnMaybeIf(String newPrefix) {
        return this.getInsertSqlColumn();
    }

    public String getInsertSqlColumn() {
        return this.ifLast ? this.column : this.column + FrameConstants.COMMA;
    }

    public String getInsertSqlPropertyMaybeIf(final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return this.getInsertSqlProperty(newPrefix);
    }

    public String getInsertSqlProperty(final String prefix) {
        String newPrefix = prefix == null ? FrameConstants.EMPTY : prefix;
        return this.ifLast ? SqlScriptUtils.safeParam(newPrefix + this.el) :
                SqlScriptUtils.safeParam(newPrefix + this.el) + FrameConstants.COMMA;
    }

    /**
     * 获取 set sql 片段
     *
     * @return sql 脚本片段
     */
    public String getSqlSet() {
        // 默认: column=
        String sqlSet = column + FrameConstants.EQUALS;
        sqlSet += SqlScriptUtils.safeParam(FrameConstants.EMPTY + el);
        sqlSet += ifLast ? FrameConstants.EMPTY : FrameConstants.COMMA;
        return sqlSet;
    }

}
