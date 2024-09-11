package me.ddreports.data;

import json2.JSONObject;

import java.util.Collection;

public class Zone {

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(State state) {
        this.state = state;
    }

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
