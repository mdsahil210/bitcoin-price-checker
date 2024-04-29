package com.example.demo.controller;

import com.example.demo.service.BpiAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.SortedMap;


@RestController
@RequestMapping("/api")
public class BpiAppController {

    @Autowired
    private BpiAppService bpiAppService;
    
    @GetMapping("/price")
    public SortedMap<String, Number> findHistoricalBitcoinPrice(
            @RequestParam String startDate, @RequestParam String endDate, @RequestParam String currency) {
        return bpiAppService.getBpiHistoricalData(startDate, endDate, currency);
    }
    

}
