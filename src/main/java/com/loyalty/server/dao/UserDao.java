package com.loyalty.server.dao;

import com.loyalty.shared.domain.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


/**
 */

public class UserDao {

    public User insertUser(User inUser)
            throws Exception {
        if (inUser == null) {
            throw new Exception("User information was null");
        }

        CallableStatement cstmt = null;
        Connection con = null;
        ResultSet theResultSet = null;
        User theSavedUser = null;
        String status = "";

        try {
            con = DatabaseConnection.getUserLoyaltyConnection();

            cstmt = con.prepareCall("{call userloyalty.Insert_User(?,?,?,?)}");
            cstmt.setString("inFirstName", inUser.getFirstName());
            cstmt.setString("inLastName", inUser.getLastName());
            cstmt.setString("inEmail", inUser.getEmail());
            cstmt.registerOutParameter("status", java.sql.Types.VARCHAR);

            boolean hasResultSet = cstmt.execute();
            status = cstmt.getString("status");

            //TODO Enum
            if(!"CREATE_SUCCESS".equals(status)){
                throw new Exception(status);
            }

            if(hasResultSet){
                theResultSet = cstmt.getResultSet();
                while(theResultSet.next()){
                    Integer id = theResultSet.getInt("id");
                    String firstName = theResultSet.getString("firstName");
                    String lastName = theResultSet.getString("lastName");
                    String email = theResultSet.getString("email");

                    theSavedUser = new User(id,email,firstName,lastName,0);
                }
            }


        } catch (Exception e) {
            //TODO log --> ("Exception occurred saving user" + e.toString());
            throw e;
        } finally {
            DatabaseConnection.closeResultSet(theResultSet);
            DatabaseConnection.closeStatement(cstmt);
            DatabaseConnection.closeConnection(con);
        }


        return theSavedUser;
    }
}
