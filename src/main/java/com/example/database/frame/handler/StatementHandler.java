//package com.example.database.frame.frame.handler;
//
//import com.example.database.frame.frame.config.Configuration;
//import com.example.database.frame.frame.config.DataSource;
//import com.example.database.frame.frame.mapper.MapperData;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class StatementHandler {
//    private final Configuration configuration;
//
//    public StatementHandler(Configuration configuration) {
//        this.configuration = configuration;
//    }
//
//    public <E> E query(MapperData mapperData, Object... parameter) {
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            conn = getConnection();
//            preparedStatement = conn.prepareStatement(mapperData.getSql());
//            ParameterHandler parameterHandler = new ParameterHandler(preparedStatement);
//            parameterHandler.setParameters(parameter);
//            preparedStatement.execute();
//            ResultHandler resultHandler = new ResultHandler(mapperData.getResultType(), preparedStatement.getResultSet());
//            return (E) resultHandler.handle();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    private Connection getConnection() throws SQLException, ClassNotFoundException {
//        DataSource dataSource = configuration.getDataSource();
//        Class.forName(dataSource.getDriver());
//        return DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
//    }
//}
