package me.ddreports.data;

import java.util.UUID;

public class Zone {

    private final UUID uid;
    private String city;
    private State state;

    public Zone() {
        uid = UUID.randomUUID();
    }

    public UUID getUid() {
        return uid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return city + ", " + state;
    }


}
