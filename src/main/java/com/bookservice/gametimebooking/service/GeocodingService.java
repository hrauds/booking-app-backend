package com.bookservice.gametimebooking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeocodingService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final String geocodingApiUrl = "https://maps.googleapis.com/maps/api/geocode/json";

    RestTemplate restTemplate = new RestTemplate();

    public String getCoordinates(String address) throws JsonProcessingException {
        String url = String.format("%s?address=%s&key=%s", geocodingApiUrl, address, apiKey);
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(response);

        JsonNode locationNode = jsonNode.at("/results/0/geometry/location");

        if (!locationNode.isEmpty()) {
            double lat = locationNode.get("lat").asDouble();
            double lng = locationNode.get("lng").asDouble();
            return ", Lat: " + lat + "   " + "Lng: " + lng;
        }

        return "";
    }
}
