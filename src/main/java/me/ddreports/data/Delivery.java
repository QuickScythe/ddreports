package me.ddreports.data;

import json2.JSONObject;

public class Delivery {

    public String store;

    public void setTips(double tips) {
        this.tips = tips;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double tips;

//    public Delivery(Store store, double tips) {
//        this.store = store;
//        this.tips = tips;
//    }
//
//    public Delivery(JSONObject data){
//        this.store = new Store(data.getJSONObject("store"));
//        this.tips = data.getDouble("tips");
//    }

    public String getStore() {
        return store;
    }

    public double getTips() {
        return tips;
    }


//    public JSONObject toJson() {
//        JSONObject data = new JSONObject();
//        data.put("store", store);
//        data.put("tips", tips);
//        return data;
//    }
}
