package com.loyalty.server.dao;

import com.loyalty.server.utility.Message;
import com.loyalty.shared.domain.Transfer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;


/**
 */

public class TransferDao {

    public Transfer insertTransfer(Transfer inTransfer)
            throws Exception {
        if (inTransfer == null) {
            throw new Exception("Transfer information was null");
        }

        CallableStatement cstmt = null;
        Connection con = null;
        ResultSet theResultSet = null;
        Transfer theSavedTransfer = null;
        String status = "";

        try {
            con = DatabaseConnection.getUserLoyaltyConnection();

            cstmt = con.prepareCall("{call userloyalty.Insert_Transfer(?,?,?)}");
            cstmt.setInt("inUserId", inTransfer.getUserId());
            cstmt.setInt("inAmount", inTransfer.getAmount());
            cstmt.registerOutParameter("status", java.sql.Types.VARCHAR);

            boolean hasResultSet = cstmt.execute();
            status = cstmt.getString("status");

            if (!"CREATE_SUCCESS".equals(status)) {
                throw new Exception(String.format(Message.valueOf(status).getMessage(),"transfer"));
            }

            if (hasResultSet) {
                theResultSet = cstmt.getResultSet();
                while (theResultSet.next()) {
                    Integer id = theResultSet.getInt("id");
                    Integer userId = theResultSet.getInt("userId");
                    Integer amount = theResultSet.getInt("amount");

                    theSavedTransfer = new Transfer(id, userId, amount);
                }
            }
        } catch (Exception e) {
            //TODO log --> ("Exception occurred saving transfer" + e.toString());
            throw e;
        } finally {
            DatabaseConnection.closeResultSet(theResultSet);
            DatabaseConnection.closeStatement(cstmt);
            DatabaseConnection.closeConnection(con);
        }


        return theSavedTransfer;
    }

    public List<Transfer> getTransfers(Integer inUserId) throws Exception{
        if (inUserId == null) {
            throw new Exception("Transfer information was null");
        }

        CallableStatement cstmt = null;
        Connection con = null;
        ResultSet theResultSet = null;
        List<Transfer> theTransfers = null;
        String status = "";

        try {
            con = DatabaseConnection.getUserLoyaltyConnection();

            cstmt = con.prepareCall("{call userloyalty.Get_Transfers(?,?,?)}");
            cstmt.setNull("inTransferId", Types.INTEGER );
            cstmt.setInt("inUserId", inUserId);
            cstmt.registerOutParameter("status", Types.VARCHAR);

            boolean hasResultSet = cstmt.execute();
            status = cstmt.getString("status");

            if (!"FETCH_SUCCESS".equals(status)) {
                throw new Exception(String.format(Message.valueOf(status).getMessage(),"transfer list"));
            }

            if (hasResultSet) {
                theResultSet = cstmt.getResultSet();
                theTransfers = new LinkedList<Transfer>();
                while (theResultSet.next()) {
                    Integer id = theResultSet.getInt("id");
                    Integer userId = theResultSet.getInt("userId");
                    Integer amount = theResultSet.getInt("amount");
                    theTransfers.add(new Transfer(id, userId, amount));
                }
            }
        } catch (Exception e) {
            //TODO log --> ("Exception occurred saving transfer" + e.toString());
            throw e;
        } finally {
            DatabaseConnection.closeResultSet(theResultSet);
            DatabaseConnection.closeStatement(cstmt);
            DatabaseConnection.closeConnection(con);
        }

        return theTransfers;
    }
}
