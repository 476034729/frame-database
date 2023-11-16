package com.example.database.frame.metadata;

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
}
