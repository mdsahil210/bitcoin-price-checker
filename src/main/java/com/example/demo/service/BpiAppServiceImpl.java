package com.example.demo.service;

import com.example.demo.api.BpiService;
import com.example.demo.api.CoinDeskApiClient;
import com.example.demo.model.BpiHistoricalData;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

@Service("BpiAppService")
public class BpiAppServiceImpl implements BpiAppService {

    private SortedMap<String, Number> cache = new TreeMap<>();

    @Override
    public SortedMap<String, Number> getBpiHistoricalData(String startDate, String endDate, String currency) {
        if(cache.containsKey(startDate) && cache.containsKey(endDate)) {
            System.out.println("Cache hit");
            return cache.subMap(startDate, endDate+"\0");
        }
        try {
            System.out.println("Cache Miss... Calling API");
            BpiService bpiService = CoinDeskApiClient.getClient().create(BpiService.class);
            BpiHistoricalData bpiHistoricalData =
                    bpiService.getBpiHistoricalData(startDate, endDate).execute().body();
            SortedMap<String, Number> bpiMap = bpiHistoricalData.getBpi();
            bpiMap.forEach((key, value) -> cache.merge(key, value, (oldVal, newVal) -> oldVal));
            return bpiMap;
        } catch (Exception e) {
            System.out.println("API call failed");
            // If the API call fails, return the best possible result from the cache
            return cache.subMap(startDate, endDate+"\0");
        }
    }
}
