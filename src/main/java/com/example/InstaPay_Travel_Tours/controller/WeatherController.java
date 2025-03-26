package com.example.InstaPay_Travel_Tours.controller;


import com.example.InstaPay_Travel_Tours.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/local-guide/{city}")
    public String getLocalGuide(@PathVariable String city) {
        return "https://www.google.com/search?q=best+places+to+visit+in+" + city;
    }


    @GetMapping("/{city}")
    public Map<String, Object> getWeather(@PathVariable String city) {
        return weatherService.getWeather(city);
    }
}
