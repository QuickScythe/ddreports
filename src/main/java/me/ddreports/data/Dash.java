package me.ddreports.data;

import java.util.ArrayList;
import java.util.List;

public class Dash {
    public String startTime;
    public String endTime;
    public double miles;
    public long activeTime;
    public List<Offer> offers = new ArrayList<>();

    public double getTotalPay(){
        double totalPay = 0;
        for(Offer offer : offers){
            totalPay += offer.getBasePay();
            for(Delivery delivery : offer.getDeliveries()){
                totalPay += delivery.getTips();
            }
        }
        return totalPay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }


}
