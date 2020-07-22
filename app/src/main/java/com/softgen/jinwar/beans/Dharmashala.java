package com.softgen.jinwar.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.softgen.jinwar.utils.Constants.empty;

public class Dharmashala {
    private String dharmashalaID;
    private String dharmashalaName;
    private String dharmashalaAddress;
    private String cityName;
    private String stateName;

    private List<DharmashalaRoom> dharmashalaRoomList;

    private Dharmashala(){

    }

    public Dharmashala(JSONObject dharmashalaDetails){
        dharmashalaID = dharmashalaDetails.optString("dharmashala_id", empty);
        dharmashalaName = dharmashalaDetails.optString("dharmashala_name", empty);
        dharmashalaAddress = dharmashalaDetails.optString("dharmashala_address", empty);
        cityName = dharmashalaDetails.optString("city_name", empty);
        stateName = dharmashalaDetails.optString("state_name", empty);

        dharmashalaRoomList = new ArrayList<>();
        JSONArray dharmashalaRoomArray;

        try {
            dharmashalaRoomArray = new JSONArray(dharmashalaDetails.optString("room_details"));

            if(dharmashalaRoomArray!=null){
                for(int roomCnt = 0; roomCnt < dharmashalaRoomArray.length();roomCnt++){
                    JSONObject roomJSONObject = (JSONObject) dharmashalaRoomArray.get(roomCnt);
                    dharmashalaRoomList.add(new DharmashalaRoom(roomJSONObject));
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public List<DharmashalaRoom> getDharmashalaRoomList() {
        return dharmashalaRoomList;
    }

    public String getDharmashalaID() {
        return dharmashalaID;
    }

    public String getDharmashalaName() {
        return dharmashalaName;
    }

    public String getDharmashalaAddress() {
        return dharmashalaAddress;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return stateName;
    }
}
