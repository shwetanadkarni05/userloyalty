package com.loyalty.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


/**
 */

public class DatabaseConnection {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String LOCAL_DATABASE_RESOURCE_NAME = "LocalDatabaseConnection";
    private static ResourceBundle LOCALDatabaseResource = ResourceBundle.getBundle(LOCAL_DATABASE_RESOURCE_NAME);


    //TODO : THIS FILE CAN BE CREATED LATER WHILE MOVING TO PRODUCTION
    /*private static final String PROD_DATABASE_RESOURCE_NAME = "ProdDatabaseConnection";
    private static ResourceBundle PRODDatabaseResource = ResourceBundle.getBundle(PROD_DATABASE_RESOURCE_NAME);*/

    public static Connection getUserLoyaltyConnection() {

        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException exc) {
            System.out.println("******** ERROR ********");
            System.out.println("Could not register mysql driver for DB1 Connection");
            System.out.println(exc);
            return conn;
        }

        try {
            /*if ("UserLoyalty-prod".equals(System.getProperty("ENV"))) {
                conn = DriverManager.getConnection(PRODDatabaseResource.getString("DSN"), DecisionDatabaseResource.getString("USER"), DecisionDatabaseResource.getString("PASS"));
            } else {*/
                conn = DriverManager.getConnection(LOCALDatabaseResource.getString("DSN"), LOCALDatabaseResource.getString("USER"), LOCALDatabaseResource.getString("PASS"));
            /*}*/
        } catch (SQLException exc) {
            System.out.println("******** ERROR ********");
            System.out.println("Could not create connection to userloyalty schema");
            System.out.println(exc);
        }

        return conn;
    }

    /**
     * Utility method to force closing of a statement.
     *
     * @param stmt the statement to attempt to close.
     */
    public static final void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {

                //itsLogger.error(e.toString());
                //itsLogger.error(e);
            }
        }
    }

    /**
     * Utility method to force closing of a connection.
     *
     * @param con the connection to attempt to close.
     */
    public static final void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {

                //itsLogger.error(e.toString());
                // itsLogger.error(e);
            }
        }
    }

    public static final void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

                // itsLogger.error(e.toString());
                // itsLogger.error(e);
            }
        }
    }
}

