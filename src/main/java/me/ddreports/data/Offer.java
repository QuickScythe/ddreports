package me.ddreports.data;

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
