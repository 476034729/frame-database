
package com.example.database.frame.metadata;


import com.example.database.frame.annotation.TableField;
import com.example.database.frame.annotation.TableId;
import com.example.database.frame.annotation.TableName;
import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlCommandType;

import javax.crypto.KeyGenerator;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;


public class TableInfoHelper {

    /**
     * 储存反射类表信息
     */
    private static final Map<Class<?>, TableInfo> TABLE_INFO_CACHE = new ConcurrentHashMap<>();

    /**
     * 储存表名对应的反射类表信息
     */
    private static final Map<String, TableInfo> TABLE_NAME_INFO_CACHE = new ConcurrentHashMap<>();

    /**
     * 默认表主键名称
     */
    private static final String DEFAULT_ID_NAME = "id";

    /**
     * <p>
     * 根据表名获取实体映射表信息
     * </p>
     *
     * @param tableName 表名
     * @return 数据库表反射信息
     */
    public static TableInfo getTableInfo(String tableName) {
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        return TABLE_NAME_INFO_CACHE.get(tableName);
    }

    /**
     * <p>
     * 获取所有实体映射表信息
     * </p>
     *
     * @return 数据库表反射信息集合
     */
    public static List<TableInfo> getTableInfos() {
        return Collections.unmodifiableList(new ArrayList<>(TABLE_INFO_CACHE.values()));
    }

    /**
     * 清空实体表映射缓存信息
     *
     * @param entityClass 实体 Class
     */
    public static void remove(Class<?> entityClass) {
        TABLE_INFO_CACHE.remove(entityClass);
    }

    /**
     * <p>
     * 实体类反射获取表信息【初始化】
     * </p>
     *
     * @param clazzs 反射实体类
     * @return 数据库表反射信息
     */
    public synchronized static void initTableInfo(Configuration configuration, Set<Class<?>> clazzs) {
        for (Class<?> clazz : clazzs) {
            TableInfo tableInfo = new TableInfo(configuration, clazz);
            TableName table = clazz.getAnnotation(TableName.class);
            tableInfo.setTableName(table.value());
            List<TableFieldInfo> tableFieldInfos = new ArrayList<>();
            for (Field field : clazz.getDeclaredFields()) {
                TableField tableField = field.getAnnotation(TableField.class);
                TableFieldInfo tableFieldInfo = new TableFieldInfo(field, tableField.value(), field.getName(), field.getType());
                TableId tableId = field.getAnnotation(TableId.class);
                if (tableId != null) {
                    tableInfo.setKeyColumn(tableId.value());
                    tableInfo.setKeyProperty(field.getName());
                    tableInfo.setKeyType(field.getType());
                }
                tableFieldInfos.add(tableFieldInfo);
            }
            tableInfo.setTableFieldInfos(tableFieldInfos);
            TABLE_INFO_CACHE.put(clazz, tableInfo);
            TABLE_NAME_INFO_CACHE.put(tableInfo.getTableName(), tableInfo);
        }
    }

}
