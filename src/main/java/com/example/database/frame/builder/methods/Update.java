package com.example.database.frame.builder.methods;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.metadata.TableInfo;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public class Update extends AbstractMethod {

    public Update(Configuration configuration) {
        super(configuration, SqlMethod.UPDATE.getMethod());
    }

    @Override
    public String getSql(TableInfo tableInfo) {
        return String.format(SqlMethod.UPDATE.getSql(), tableInfo.getTableName(),tableInfo.getAllSqlSet());
    }

}
