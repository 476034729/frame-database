package com.example.database.frame.metadata;

import com.example.database.frame.config.Configuration;

import java.util.List;

/**
 * 数据库表反射信息
 *
 * @author hubin
 * @since 2016-01-23
 */


public class TableInfo {
    private Configuration configuration;
    /**
     * 实体类型
     */
    private Class<?> entityType;
    /**
     * 表名称
     */
    private String tableName;

    private List<TableFieldInfo> tableFieldInfos;
    /**
     * 表主键ID 字段名
     */
    private String keyColumn;
    /**
     * 表主键ID 属性名
     */
    private String keyProperty;
    /**
     * 表主键ID 属性类型
     */
    private Class<?> keyType;


    /**
     * @param configuration 配置对象
     * @param entityType    实体类型
     * @since 3.4.4
     */
    public TableInfo(Configuration configuration, Class<?> entityType) {
        this.configuration = configuration;
        this.entityType = entityType;
    }

    public List<TableFieldInfo> getTableFieldInfos() {
        return tableFieldInfos;
    }

    public void setTableFieldInfos(List<TableFieldInfo> tableFieldInfos) {
        this.tableFieldInfos = tableFieldInfos;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Class<?> getEntityType() {
        return entityType;
    }

    public void setEntityType(Class<?> entityType) {
        this.entityType = entityType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public String getKeyProperty() {
        return keyProperty;
    }

    public void setKeyProperty(String keyProperty) {
        this.keyProperty = keyProperty;
    }

    public Class<?> getKeyType() {
        return keyType;
    }

    public void setKeyType(Class<?> keyType) {
        this.keyType = keyType;
    }
}
