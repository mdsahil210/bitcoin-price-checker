package com.example.bitcoinpricechecker.service;

import java.util.SortedMap;

public interface BpiAppService {
    public SortedMap<String, Number> getBpiHistoricalData(String startDate, String endDate, String currency);
}
