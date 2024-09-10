package me.ddreports.data;

import json2.JSONArray;
import json2.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dash {
    private final long start_time;
    private final long end_time;
    private final double pay;
    private final double tips;
    private final double miles;
    private final long active_time;
    private final List<Offer> offers;
    private final double total_pay;

    public Dash(long start_time, long end_time, double miles, long active_time, List<Offer> offers) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.miles = miles;
        this.active_time = active_time;
        this.offers = offers;
        this.tips = calculateTips();
        this.pay = calculatePay();
        this.total_pay = tips + pay;
    }

    public Dash(JSONObject data){
        this.start_time = data.getLong("start_time");
        this.end_time = data.getLong("end_time");
        this.miles = data.getDouble("miles");
        this.active_time = data.getLong("active_time");
        JSONArray offerData = data.getJSONArray("offers");
        List<Offer> offers = new ArrayList<>();
        for (int i = 0; i < offerData.length(); i++) {
            offers.add(new Offer(offerData.getJSONObject(i)));
        }
        this.offers = offers;
        this.tips = calculateTips();
        this.pay = calculatePay();
        this.total_pay = tips + pay;

    }

    private void setVariables() {
    }

    private double calculatePay() {
        return 0;
    }

    private double calculateTips() {
        double total = 0;
        for (Offer offer : offers) {
            total += offer.getTips();
        }
        return total;
    }
}
