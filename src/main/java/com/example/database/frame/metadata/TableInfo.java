package com.example.database.frame.metadata;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.util.SqlScriptUtils;
import com.example.database.frame.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private List<TableFieldInfo> fieldList;

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
        return fieldList;
    }

    public void setTableFieldInfos(List<TableFieldInfo> fieldList) {
        this.fieldList = fieldList;
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

    public boolean havePK() {
        return StringUtils.isNotBlank(this.keyColumn);
    }

    public String getAllInsertSqlColumnMaybeIf(final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return this.getKeyInsertSqlColumn(false, true) + (String) this.fieldList.stream().map((i) -> {
            return i.getInsertSqlColumnMaybeIf(newPrefix);
        }).filter(Objects::nonNull).collect(Collectors.joining("\n"));
    }

    public String getKeyInsertSqlColumn(final boolean batch, final boolean newLine) {
        if (this.havePK()) {
            return this.keyColumn + "," + (newLine ? "\n" : "");
        } else {
            return "";
        }
    }

    public String getAllInsertSqlPropertyMaybeIf(final String prefix) {
        String newPrefix = prefix == null ? "" : prefix;
        return this.getKeyInsertSqlProperty(false, newPrefix, true) + (String)this.fieldList.stream().map((i) -> {
            return i.getInsertSqlPropertyMaybeIf(newPrefix);
        }).filter(Objects::nonNull).collect(Collectors.joining("\n"));
    }


    public String getKeyInsertSqlProperty(final boolean batch, final String prefix, final boolean newLine) {
        String newPrefix = prefix == null ? "" : prefix;
        if (this.havePK()) {
            String prefixKeyProperty = newPrefix + this.keyProperty;
            String keyColumn = SqlScriptUtils.safeParam(prefixKeyProperty) + ",";
            return keyColumn + (newLine ? "\n" : "");
        } else {
            return "";
        }
    }
}
