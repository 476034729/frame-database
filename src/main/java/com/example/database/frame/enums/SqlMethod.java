//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.database.frame.enums;

public enum SqlMethod {
    INSERT_ONE("insert", "插入一条数据（选择字段插入）", "INSERT INTO %s %s VALUES %s"),
    DELETE_BY_ID("deleteById", "根据ID 删除一条数据", "DELETE FROM %s WHERE %s=#{%s}"),
    DELETE("delete", "根据 entity 条件删除记录", "DELETE FROM %s "),
    UPDATE_BY_ID("updateById", "根据ID 选择修改数据", "UPDATE %s SET %s WHERE %s=#{%s}"),
    UPDATE("update", "根据 whereEntity 条件，更新记录", "UPDATE %s SET %s"),
    SELECT_BY_ID("selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s}"),
    SELECT_LIST("selectList", "查询多个数据", "SELECT %s FROM %s");


    private final String method;
    private final String desc;
    private final String sql;

    private SqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return this.method;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSql() {
        return this.sql;
    }
}
