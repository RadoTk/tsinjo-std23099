package com.example.tsinjo.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class VolaClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    public VolaClient(
            @Value("${vola.api.url}") String baseUrl,
            @Value("${vola.api.key}") String apiKey
    ) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public String checkPaymentStatus(String ref) {
        String url = baseUrl + "/payments/" + ref;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    JsonNode.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().get("status").asText(); // 🔁 "SUCCEEDED", "FAILED", "VERIFYING"
            } else {
                System.err.println("Erreur Vola: " + response.getStatusCode());
                return "VERIFYING";
            }
        } catch (Exception e) {
            System.err.println("Erreur exception Vola: " + e.getMessage());
            return "VERIFYING";
        }
    }
}
