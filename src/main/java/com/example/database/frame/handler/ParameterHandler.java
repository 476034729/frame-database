package com.example.database.frame.handler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ParameterHandler {
    private final PreparedStatement preparedStatement;

    public ParameterHandler(PreparedStatement statement){
        this.preparedStatement = statement;
    }

    public void setParameters(Object... parameters){
        try{
            for (int i = 0;i<parameters.length;i++) {
                if (parameters[i] instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Long) {
                    preparedStatement.setLong(i + 1, (Long) parameters[i]);
                } else if (parameters[i] instanceof String) {
                    preparedStatement.setString(i + 1, String.valueOf(parameters[i]));
                } else if (parameters[i] instanceof Boolean) {
                    preparedStatement.setBoolean(i + 1, (Boolean) parameters[i]);
                } else if (parameters[i] instanceof Date) {
                    preparedStatement.setDate(i + 1, (Date) parameters[i]);
                } else {
                    preparedStatement.setString(i + 1, String.valueOf(parameters[i]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
