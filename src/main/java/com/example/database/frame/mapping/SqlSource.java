package com.example.database.frame.mapping;

import java.util.List;

/**
 * @author tz
 * @createDate 2023-11-16
 */
public class SqlSource {

    private final String sql;
    private final List<String> params;

    public SqlSource(String sql, List<String> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public List<String> getParams() {
        return params;
    }
}
