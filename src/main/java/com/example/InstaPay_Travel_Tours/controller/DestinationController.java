package com.example.InstaPay_Travel_Tours.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DestinationController {

    private final List<Map<String, Object>> destinations = List.of(
            Map.of("city", "Kyoto", "country", "Japan", "tip", "Discover cherry blossoms in April!", "images", List.of("kyoto1.jpg", "kyoto2.jpg", "kyoto3.jpg"), "lat", 35.0116, "lng", 135.7681),
            Map.of("city", "Paris", "country", "France", "tip", "City of lights and love!", "images", List.of("paris1.jpg", "paris2.jpg", "paris3.jpg"), "lat", 48.8566, "lng", 2.3522),
            Map.of("city", "Rome", "country", "Italy", "tip", "Walk through ancient ruins!", "images", List.of("rome1.jpg", "rome2.jpg", "rome3.jpg"), "lat", 41.9028, "lng", 12.4964),
            Map.of("city", "Bali", "country", "Indonesia", "tip", "Surf and serenity await!", "images", List.of("bali1.jpg", "bali2.jpg", "bali3.jpg"), "lat", -8.4095, "lng", 115.1889),
            Map.of("city", "Reykjavik", "country", "Iceland", "tip", "Chase the northern lights!", "images", List.of("iceland1.jpg", "iceland2.jpg", "iceland3.jpg"), "lat", 64.1355, "lng", -21.8954)
    );

    private final LinkedList<Map<String, Object>> history = new LinkedList<>();

    @GetMapping("/random-destination")
    public Map<String, Object> getRandomDestination() {
        int index = new Random().nextInt(destinations.size());
        Map<String, Object> randomDestination = destinations.get(index);

        // Add to history, keeping the last 5 results
        if (history.size() >= 5) {
            history.removeFirst();
        }
        history.add(randomDestination);

        return randomDestination;
    }

    @GetMapping("/history")
    public List<Map<String, Object>> getHistory() {
        return history;
    }
}
