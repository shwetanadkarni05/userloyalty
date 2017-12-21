package com.loyalty.server.dao;

import com.loyalty.shared.domain.Transfer;


/**
 */

public class TransferDao {

    public Transfer insertTransfer(Transfer inTransfer)
            throws Exception {
        //TODO DB CALL
        Transfer theSavedTransfer = new Transfer(1,inTransfer.getUserId(),inTransfer.getPoints());
        return theSavedTransfer;
    }
}
