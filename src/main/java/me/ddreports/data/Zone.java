package me.ddreports.data;

import json2.JSONObject;

public class Zone {

    private String city;
    private State state;




    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public String getName() {
        return city + ", " + state;
    }
}
