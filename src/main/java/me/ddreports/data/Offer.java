package me.ddreports.data;

import java.util.ArrayList;
import java.util.List;

public class Offer {

    public double basePay;
    public double tips;
    public List<Delivery> deliveries = new ArrayList<>();

    public double getBasePay() {
        return basePay;
    }

    public void setBasePay(double basePay) {
        this.basePay = basePay;
    }

    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }


}
