package com.softgen.jinwar.beans;

import org.json.JSONObject;
import static com.softgen.jinwar.utils.Constants.empty;

public class AccountOwner extends User {
    private static AccountOwner accountOwner = null;
    private static final Object singleTonLock = new Object();

    private AccountOwner(JSONObject userDetails){
        super(userDetails);
    }

    public static AccountOwner getAccountOwner(JSONObject userDetails){
        if(accountOwner == null || accountOwner.memberid.equals(empty)){
            synchronized (singleTonLock){
                if(accountOwner == null || accountOwner.memberid.equals(empty)){
                    accountOwner = new AccountOwner(userDetails);
                }
            }
        }

        return accountOwner;
    }

    public static AccountOwner getAccountOwner(){
        return accountOwner;
    }
}
