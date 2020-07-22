package com.softgen.jinwar.beans;

import androidx.annotation.NonNull;

import org.json.JSONObject;
import static com.softgen.jinwar.utils.Constants.empty;

public class DharmashalaRoom {
    String roomID;
    String roomNumber;

    String washroomType;
    String coolingSystem;

    double roomBaseCharges;
    double roomOnlineCharges;
    double roomPaymentCharges;

    public DharmashalaRoom(JSONObject roomDetails){
        roomID = roomDetails.optString("room_id", empty);
        roomNumber = roomDetails.optString("room_number", empty);

        washroomType = roomDetails.optString("room_washroom_type", empty);
        coolingSystem = roomDetails.optString("room_cooling_system", empty);

        roomBaseCharges = roomDetails.optDouble("room_base_charges", 0.0);
        roomOnlineCharges = roomDetails.optDouble("room_online_charges", 0.0);
        roomPaymentCharges = roomDetails.optDouble("room_payment_charges", 0.0);
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getWashroomType() {
        return washroomType;
    }

    public String getCoolingSystem() {
        return coolingSystem;
    }

    public double getRoomBaseCharges() {
        return roomBaseCharges;
    }

    public double getRoomOnlineCharges() {
        return roomOnlineCharges;
    }

    public double getRoomPaymentCharges() {
        return roomPaymentCharges;
    }

    @Override
    @NonNull
    public String toString(){
        return roomID+" "+washroomType+" "+coolingSystem+" "+roomBaseCharges+" "+roomOnlineCharges+" "+roomPaymentCharges;
    }
}
