package me.ddreports.data;

public class Delivery {

    private final String store;
    private final double tips;

    public Delivery(String store, double tips) {
        this.store = store;
        this.tips = tips;
    }

    public String getStore() {
        return store;
    }

    public double getTips() {
        return tips;
    }
}
