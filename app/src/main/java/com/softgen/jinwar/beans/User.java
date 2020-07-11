package com.softgen.jinwar.beans;

import org.json.JSONException;
import org.json.JSONObject;

import static com.softgen.jinwar.utils.Constants.empty;

public class User {
    String memberid = empty;

    String firstName = empty;
    String lastName = empty;
    int age = 0;

    protected User(){

    }

    public User(JSONObject userDetails){
        //TODO: Change this

        memberid = userDetails.optString("member_id", empty);
        firstName = userDetails.optString("first_name", empty);
        lastName = userDetails.optString("last_name", empty);
    }

    public JSONObject getUserDetailsJson(){
        JSONObject userDetails = new JSONObject();

        try {
            userDetails.put("member_id", memberid);
            userDetails.put("first_name", firstName);
            userDetails.put("last_name", lastName);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return userDetails;
    }

    public String toString(){
        return getUserDetailsJson().toString();
    }

    public boolean isValidUser(){
        if(memberid.equals(empty)) return false;
        return true;
    }
}
