package com.example.database.frame.builder.methods;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.metadata.TableInfo;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public class SelectList extends AbstractMethod {

    public SelectList(Configuration configuration) {
        super(configuration, SqlMethod.SELECT_LIST.getMethod());
    }

    @Override
    public String getSql(TableInfo tableInfo) {
        return String.format(SqlMethod.SELECT_LIST.getSql(), tableInfo.getAllSqlSelect(), tableInfo.getTableName());
    }

}
