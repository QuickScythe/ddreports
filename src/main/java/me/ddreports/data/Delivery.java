package me.ddreports.data;

import json2.JSONObject;

public class Delivery {

    private final String store;
    private final double tips;

    public Delivery(String store, double tips) {
        this.store = store;
        this.tips = tips;
    }

    public Delivery(JSONObject data){
        this.store = data.getString("store");
        this.tips = data.getDouble("tips");
    }

    public String getStore() {
        return store;
    }

    public double getTips() {
        return tips;
    }
}
