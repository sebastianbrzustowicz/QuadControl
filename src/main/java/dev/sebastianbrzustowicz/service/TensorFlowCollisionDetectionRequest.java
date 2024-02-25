package dev.sebastianbrzustowicz.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class TensorFlowCollisionDetectionRequest {

    public static String performInference(double[][] inputData, String servingEndpoint) throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();

        String jsonInput = new ObjectMapper().writeValueAsString(Map.of("instances", inputData));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(servingEndpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}