package com.example.InstaPay_Travel_Tours.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:63342") // Allow cross-origin requests for development
@RequestMapping("/chat")
public class ChatController {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody String message) {
        try {
            // OpenAI API endpoint for chat model
            String url = "https://api.openai.com/v1/chat/completions";  // Correct endpoint for GPT-3.5

            // Create the payload for the request
            JSONObject payload = new JSONObject();
            payload.put("model", "gpt-3.5-turbo");  // Make sure the model parameter is included
            JSONArray messages = new JSONArray();

            // Construct the message object for the API request
            JSONObject messageJson = new JSONObject();
            messageJson.put("role", "user").put("content", message);  // The user message
            messages.put(messageJson);

            payload.put("messages", messages);
            payload.put("max_tokens", 150);  // Limit the response length

            // Create headers for the API request
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + openaiApiKey);  // Add the API key for authorization
            HttpEntity<String> entity = new HttpEntity<>(payload.toString(), headers);

            // Send the POST request to OpenAI API and capture the response
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            // Parse the AI response from the API's response body
            JSONObject responseJson = new JSONObject(response.getBody());
            String aiResponse = responseJson.getJSONArray("choices")
                    .getJSONObject(0)
                    .getString("message");

            // Return the AI response to the client
            return ResponseEntity.ok(aiResponse);

        } catch (Exception e) {
            // Log the exception and return a 500 response with the error message
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
