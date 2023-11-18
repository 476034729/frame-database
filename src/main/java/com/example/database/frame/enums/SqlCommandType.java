package com.example.database.frame.enums;

public enum SqlCommandType {
    INSERT("insert"), UPDATE("update"), DELETE("delete"), SELECT("select");

    private final String val;

    SqlCommandType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public static SqlCommandType getType(String str) {
        if (str.startsWith(INSERT.val)) {
            return INSERT;
        } else if (str.startsWith(UPDATE.val)) {
            return UPDATE;
        } else if (str.startsWith(DELETE.val)) {
            return DELETE;
        } else if (str.startsWith(SELECT.val)) {
            return SELECT;
        } else {
            return null;
        }
    }
}
