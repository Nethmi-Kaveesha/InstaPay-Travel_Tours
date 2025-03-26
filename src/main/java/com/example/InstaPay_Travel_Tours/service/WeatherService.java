package com.example.InstaPay_Travel_Tours.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class WeatherService {
    private static final String API_KEY = "1f63215819e1a497a27e13f440eaf8ec";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public Map<String, Object> getWeather(String city) {
        String url = String.format(BASE_URL, city, API_KEY);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Map.class);
    }
}
