package com.example.demo.model;

import java.util.SortedMap;

public class BpiHistoricalData {
    private SortedMap<String, Number> bpi;
    private String disclaimer;
    private Time time;

    public SortedMap<String, Number> getBpi() {
        return bpi;
    }

    public void setBpi(SortedMap<String, Number> bpi) {
        this.bpi = bpi;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
