package com.loyalty.server.dao;

import com.loyalty.shared.domain.Transfer;

import java.util.LinkedList;
import java.util.List;


/**
 */

public class TransferDao {

    public Transfer insertTransfer(Transfer inTransfer)
            throws Exception {
        //TODO DB CALL
        Transfer theSavedTransfer = new Transfer(1,inTransfer.getUserId(),inTransfer.getPoints());
        return theSavedTransfer;
    }

    public List<Transfer> getTransfers(Integer inUserId){
        List<Transfer> theTransfers = new LinkedList<Transfer>();
        theTransfers.add(new Transfer(1,1,20));
        theTransfers.add(new Transfer(2,1,-5));
        return theTransfers;
    }
}
