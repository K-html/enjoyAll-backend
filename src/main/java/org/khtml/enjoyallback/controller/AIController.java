package org.khtml.enjoyallback.controller;

import lombok.RequiredArgsConstructor;
import org.khtml.enjoyallback.api.Api_Response;
import org.khtml.enjoyallback.service.BoardService;
import org.khtml.enjoyallback.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/chat")
public class AIController {
    @Value("${ai-server.origin}")
    private String AI_SERVER_ORIGIN;
    private final RestTemplate restTemplate;

    public AIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Api_Response<String>> askAI(
            @RequestHeader HttpHeaders headers,
            @RequestBody String query) {
        HttpEntity<String> entity = new HttpEntity<>(query, headers);
        ResponseEntity<String> aiServerResponse = restTemplate.exchange(
                AI_SERVER_ORIGIN,
                HttpMethod.POST,
                entity,
                String.class);
        return ApiResponseUtil.createSuccessResponse(aiServerResponse.getBody());
    }
}
