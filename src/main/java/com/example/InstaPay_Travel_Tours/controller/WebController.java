package com.example.InstaPay_Travel_Tours.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/map")
    public String showMap() {
        return "map"; // Returns map.html
    }
}
