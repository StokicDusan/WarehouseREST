package com.etf.RMS.dao;

import com.etf.RMS.exception.WarehouseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dusan
 */
public class ResourcesManager {

    private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:3308/warehouse?user=root&password=";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(JDBC_CONNECTION_STRING);
        return con;
    }

    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeConnection(Connection con) throws WarehouseException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new WarehouseException("Failed to close database connection.", ex);
            }
        }
    }

    public static void rollbackTransactions(Connection con) throws WarehouseException {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new WarehouseException("Failed to rollback database transactions.", ex);
            }
        }
    }
}
