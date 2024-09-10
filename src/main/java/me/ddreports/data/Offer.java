package me.ddreports.data;

import json2.JSONArray;
import json2.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Offer {

    private final double base_pay;
    private final double tips;

    private final List<Delivery> deliveries;

    public Offer(double base_pay, List<Delivery> deliveries) {
        this.base_pay = base_pay;
        this.deliveries = deliveries;
        this.tips = calculateTips();
    }

    public Offer(JSONObject data){
        this.base_pay = data.getDouble("base_pay");
        this.tips = data.getDouble("tips");
        JSONArray deliveryData = data.getJSONArray("deliveries");
        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < deliveryData.length(); i++) {
            deliveries.add(new Delivery(deliveryData.getJSONObject(i)));
        }
        this.deliveries = deliveries;
    }

    private double calculateTips() {
        double total = 0;
        for (Delivery delivery : deliveries) {
            total += delivery.getTips();
        }
        return total;
    }

    public double getBasePay() {
        return base_pay;

    }


    public double getTips() {
        return tips;
    }
}
