package com.example.database.frame.builder.methods;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.metadata.TableInfo;
import com.example.database.frame.util.SqlScriptUtils;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public class Insert extends AbstractMethod {

    public Insert(Configuration configuration) {
        super(configuration, SqlMethod.INSERT_ONE.getMethod());
    }

    @Override
    public String getSql(TableInfo tableInfo) {
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf((String) null), "(", ")", (String) null, ",");
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf((String) null), "(", ")", (String) null, ",");
        return String.format(SqlMethod.INSERT_ONE.getSql(), tableInfo.getTableName(), columnScript, valuesScript);
    }
}
