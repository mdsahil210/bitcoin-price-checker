package com.example.demo.api;

import com.example.demo.model.BpiHistoricalData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BpiService {

    @GET("/v1/bpi/historical/close.json")
    public Call<BpiHistoricalData> getBpiHistoricalData(
            @Query("start") String startDate,
            @Query("end") String endDate);
}
