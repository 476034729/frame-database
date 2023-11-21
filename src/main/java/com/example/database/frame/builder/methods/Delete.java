package com.example.database.frame.builder.methods;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.metadata.TableInfo;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public class Delete extends AbstractMethod {

    public Delete(Configuration configuration) {
        super(configuration, SqlMethod.DELETE.getMethod());
    }

    @Override
    public String getSql(TableInfo tableInfo) {
        return String.format(SqlMethod.DELETE.getSql(), tableInfo.getTableName());
    }

}
