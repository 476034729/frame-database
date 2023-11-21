package com.example.database.frame.builder.methods;

import com.example.database.frame.builder.AnnoMapperBuilder;
import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.metadata.TableInfo;
import com.example.database.frame.metadata.TableInfoHelper;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public class UpdateById extends AbstractMethod {

    public UpdateById(Configuration configuration) {
        super(configuration, SqlMethod.UPDATE_BY_ID.getMethod());
    }

    @Override
    public String getSql(TableInfo tableInfo) {
        return String.format(SqlMethod.UPDATE_BY_ID.getSql(), tableInfo.getTableName(),
                tableInfo.getAllSqlSet(), tableInfo.getKeyColumn(),  tableInfo.getKeyProperty());
    }



}
