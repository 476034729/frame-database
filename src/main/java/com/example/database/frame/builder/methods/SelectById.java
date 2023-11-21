package com.example.database.frame.builder.methods;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.metadata.TableInfo;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public class SelectById extends AbstractMethod {

    public SelectById(Configuration configuration) {
        super(configuration, SqlMethod.SELECT_BY_ID.getMethod());
    }

    @Override
    public String getSql(TableInfo tableInfo) {
        return String.format(SqlMethod.SELECT_BY_ID.getSql(),
                tableInfo.getAllSqlSelect(),
                tableInfo.getTableName(),
                tableInfo.getKeyColumn(),
                tableInfo.getKeyProperty());
    }

}
