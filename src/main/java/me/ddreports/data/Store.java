package me.ddreports.data;

import json2.JSONObject;

public class Store {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    String name;
    Zone zone;

}
