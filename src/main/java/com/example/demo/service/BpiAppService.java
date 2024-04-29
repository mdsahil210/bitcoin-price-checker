package com.example.demo.service;

import java.util.SortedMap;

public interface BpiAppService {
    public SortedMap<String, Number> getBpiHistoricalData(String startDate, String endDate, String currency);
}
