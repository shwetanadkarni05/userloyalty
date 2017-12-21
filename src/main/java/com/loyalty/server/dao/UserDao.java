package com.loyalty.server.dao;

import com.loyalty.shared.domain.Transaction;
import com.loyalty.shared.domain.User;

import java.util.LinkedList;


/**
 */

public class UserDao {

    public User insertUser(User inUser) throws Exception{
        //TODO DB CALL
        User theSavedUser = new User(1,inUser.getEmail(),inUser.getFirstName(),inUser.getLastName(),0,new LinkedList<Transaction>());

        return theSavedUser;
    }
}
